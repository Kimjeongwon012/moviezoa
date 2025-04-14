package me.jeong.movieinfo.external;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import me.jeong.movieinfo.service.MovieService;
import me.jeong.movieinfo.service.dto.MoviePopularityDTO;
import me.jeong.movieinfo.service.mapper.ActorMapper;
import me.jeong.movieinfo.service.mapper.MovieMapper;
import me.jeong.movieinfo.service.dto.MovieDTO;
import me.jeong.movieinfo.utils.DateUtils;
import me.jeong.movieinfo.controller.MovieController;
import me.jeong.movieinfo.domain.Actor;
import me.jeong.movieinfo.domain.Movie;
import me.jeong.movieinfo.repository.ActorRepository;
import me.jeong.movieinfo.repository.MovieRepository;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

@Component
public class TmdbAPI {
    private static final Logger log = LoggerFactory.getLogger(MovieController.class);
    private static final int TOTAL_REQUEST_MONTHS = 6;
    private static final int BATCH_REQUEST_MONTHS = 2;
    private static final String API_URL = "https://api.themoviedb.org/3/";
    private static final String API_KEY = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiNjg2YmQ3ZDc5ZjI5YjI0ZmIzODk3OGI1YTk2MWNkZSIsIm5iZiI6MTczOTk1OTQ4MS42MDE5OTk4LCJzdWIiOiI2N2I1YWNiOTIxNTI2MzhmNWVlM2MyMDkiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.qgOWsfIxE4IlUZaobU-vDr9yQ36ire_fLZgoO3Wyf04";
    private final ActorRepository actorRepository;
    private final MovieRepository movieRepository;
    private final MoviePopularityUpdater moviePopularityUpdater;
    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public TmdbAPI(MovieRepository movieRepository, ActorRepository actorRepository, MoviePopularityUpdater moviePopularityUpdater) {
        this.movieRepository = movieRepository;
        this.actorRepository = actorRepository;
        this.moviePopularityUpdater = moviePopularityUpdater;
    }

    public MovieDTO fetchMovieDetails(Long movieId) {
        try {
            String url = UriComponentsBuilder.fromHttpUrl(API_URL + "movie/" + movieId)
                    .queryParam("append_to_response", "credits,images,genres,videos,release_dates")
                    .queryParam("language", "ko-KR")
                    .queryParam("include_image_language", "null")
                    .toUriString();
            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .addHeader("accept", "application/json")
                    .addHeader("Authorization", "Bearer " + API_KEY)
                    .build();

            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();
            response.close();

            MovieDTO dto = MovieMapper.mapToMovieDTO(responseBody);
            return dto;
        } catch (Exception e) {
            log.info("영화 상세 정보를 가져오다가 오류 발생", e);
        }
        return null;
    }

    public void fetchAndStoreMovies() {
        List<Movie> movieList = new ArrayList<>();

        Set<Long> movieIds = movieRepository.findAllIds();
        String[][] periods = DateUtils.splitMonthsIntoPeriods(TOTAL_REQUEST_MONTHS, BATCH_REQUEST_MONTHS);

        try {
            for (int i = 0; i < periods.length; i++) {
                movieList.addAll(fetchMoviesByPeriod(periods[i][0], periods[i][1], movieIds));
                log.info("[{}/{}] 영화 요청 완료", i + 1, periods.length);
            }
            movieRepository.saveAll(movieList);
        } catch (Exception e) {
            log.error("영화 데이터를 저장하는 중 오류 발생", e);
        }
    }

    public void updateRecentPopularity() {
        List<Movie> movieList = new ArrayList<>();
        List<MoviePopularityDTO> popularityList = new ArrayList<>();
        String[][] periods = DateUtils.splitMonthsIntoPeriods(TOTAL_REQUEST_MONTHS, BATCH_REQUEST_MONTHS);
        Set<Long> emptyList = new HashSet<>();

        try {
            for (int i = 0; i < periods.length; i++) {
                // 해당 기간(start~end)의 영화 목록 가져오기
                movieList = fetchMoviesByPeriod(periods[i][0], periods[i][1], emptyList);

                // 가져온 영화에서 ID와 인기도만 추출하여 DTO 리스트에 저장
                for (Movie movie : movieList) {
                    popularityList.add(new MoviePopularityDTO(movie.getId(), movie.getPopularity()));
                }

                // 추출한 인기도 리스트를 갱신 처리
                moviePopularityUpdater.batchUpdatePopularity(popularityList);
                popularityList.clear();

                log.info("[{}/{}] 인지도 갱신 진행 중", i + 1, periods.length);
            }
            log.info("인지도 갱신 완료", periods.length);
        } catch (Exception e) {
            log.error("전체 영화 인지도 갱신 중 오류 발생", e);
        }
    }

    private List<Movie> fetchMoviesByPeriod(String startDate, String endDate, Set<Long> movieIds) throws Exception {
        List<Movie> movieList = new ArrayList<>();
        int page = 1;

        while (true) {
            String url = API_URL + "discover/movie?"
                    + "language=ko-KR"
                    + "&include_adult=false"
                    + "&include_video=true"
                    + "&region=KR"
                    + "&with_release_type=3"
                    + "&page=" + page
                    + "&primary_release_date.gte=" + startDate
                    + "&primary_release_date.lte=" + endDate
                    + "&sort_by=popularity.desc";

            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .addHeader("accept", "application/json")
                    .addHeader("Authorization", "Bearer " + API_KEY)
                    .build();

            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();
            response.close();

            Map<String, Object> jsonMap = objectMapper.readValue(responseBody, Map.class);
            List<Map<String, Object>> movies = (List<Map<String, Object>>) jsonMap.get("results");
            int totalPages = (int) jsonMap.get("total_pages");

            for (Map<String, Object> movieData : movies) {
                Movie movie = MovieMapper.mapToMovie(movieData);
                if (!movieIds.contains(movie.getId()) && movie.getTitle().matches("^[가-힣0-9\\s\\p{P}]+$")) {
                    movieIds.add(movie.getId());
                    movieList.add(movie);
                }
            }

            if (page++ >= totalPages) break;
        }
        return movieList;
    }

}

