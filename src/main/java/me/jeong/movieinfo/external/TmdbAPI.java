package me.jeong.movieinfo.external;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class TmdbAPI {
    private static final Logger log = LoggerFactory.getLogger(MovieController.class);
    private static final int TOTAL_REQUEST_MONTHS = 6;
    private static final int BATCH_REQUEST_MONTHS = 2;
    private static final String API_URL = "https://api.themoviedb.org/3/";
    private static final String API_KEY = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiNjg2YmQ3ZDc5ZjI5YjI0ZmIzODk3OGI1YTk2MWNkZSIsIm5iZiI6MTczOTk1OTQ4MS42MDE5OTk4LCJzdWIiOiI2N2I1YWNiOTIxNTI2MzhmNWVlM2MyMDkiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.qgOWsfIxE4IlUZaobU-vDr9yQ36ire_fLZgoO3Wyf04";
    private final ActorRepository actorRepository;
    private final MovieRepository movieRepository;
    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public TmdbAPI(MovieRepository movieRepository, ActorRepository actorRepository) {
        this.movieRepository = movieRepository;
        this.actorRepository = actorRepository;
    }

    public MovieDTO fetchMovieDetails(Long movieId) {
        try {
            String url = UriComponentsBuilder.fromHttpUrl(API_URL + "movie/" + movieId)
                    .queryParam("append_to_response", "credits,images,genres,videos")
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
            log.info(responseBody);
            MovieDTO dto = MovieMapper.mapToMovieDTO(responseBody);
            return dto;
        } catch (Exception e) {
            log.info("영화 상세 정보를 가져오다가 오류 발생", e);
        }
        return null;
    }

    public void fetchAndStoreActors() {
        List<Actor> actorList = new ArrayList<>();

        Set<Long> actorIds = actorRepository.findAllIds();

        try {
            for (int page = 1; page < 500; page++) {
                // API 요청 URL 생성
                String url = API_URL + "person/popular?"
                        + "language=ko-KR"
                        + "&page=" + page
                        + "&sort_by=popularity.desc";

                // API 요청 생성
                Request request = new Request.Builder()
                        .url(url)
                        .get()
                        .addHeader("accept", "application/json")
                        .addHeader("Authorization", "Bearer " + API_KEY)
                        .build();

                // API 요청 실행 및 응답 받기
                Response response = client.newCall(request).execute();
                String responseBody = response.body().string();
                response.close();

                // JSON 응답 데이터를 Map 형식으로 변환
                Map<String, Object> jsonMap = objectMapper.readValue(responseBody, Map.class);
                List<Map<String, Object>> actors = (List<Map<String, Object>>) jsonMap.get("results");

                // 응답받은 배우 데이터를 리스트에 추가
                for (Map<String, Object> actorData : actors) {
                    Actor actor = ActorMapper.mapToActor(actorData);

                    // 중복 데이터 확인
                    if (!actorIds.contains(actor.getId())) {
                        actorIds.add(actor.getId()); // 중복 방지를 위해 ID 저장
                        actorList.add(actor);
                    }
                }
                // 로그 출력: 현재 진행 상황 표시 (요청 완료 메시지)
                log.info("[ " + (page + 1) + "/6 ] TmdbAPI 배우 요청 완료");

            }

            // 수집한 영화 데이터를 DB에 저장
            actorRepository.saveAll(actorList);

        } catch (Exception e) {
            log.error("배우 데이터를 저장하는 중 오류 발생", e);
        }
    }

    public void fetchAndStoreMovies() {
        // 영화 데이터를 저장할 리스트
        List<Movie> movieList = new ArrayList<>();

        // 기존 DB에 저장된 영화 ID 목록을 가져와 중복을 방지
        Set<Long> movieIds = movieRepository.findAllIds();

        try {
            // 요청할 기간을 2개월 단위로 나누어 배열 생성
            String[][] periods = DateUtils.splitMonthsIntoPeriods(TOTAL_REQUEST_MONTHS, BATCH_REQUEST_MONTHS);

            // 나눠진 기간 개수만큼 반복 (예: 6개월 / 2개월 = 3회)
            for (int i = 0; i < TOTAL_REQUEST_MONTHS / BATCH_REQUEST_MONTHS; i++) {
                String startDate = periods[i][0]; // 시작 날짜
                String endDate = periods[i][1];   // 종료 날짜

                int page = 1; // 페이지 번호 초기화

                while (true) { // 페이지네이션 처리
                    // API 요청 URL 생성
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

                    // API 요청 생성
                    Request request = new Request.Builder()
                            .url(url)
                            .get()
                            .addHeader("accept", "application/json")
                            .addHeader("Authorization", "Bearer " + API_KEY)
                            .build();

                    // API 요청 실행 및 응답 받기
                    Response response = client.newCall(request).execute();
                    String responseBody = response.body().string();
                    response.close();

                    // JSON 응답 데이터를 Map 형식으로 변환
                    Map<String, Object> jsonMap = objectMapper.readValue(responseBody, Map.class);
                    List<Map<String, Object>> movies = (List<Map<String, Object>>) jsonMap.get("results");
                    int totalPages = (int) jsonMap.get("total_pages"); // 전체 페이지 수

                    // 응답받은 영화 데이터를 리스트에 추가
                    for (Map<String, Object> movieData : movies) {
                        Movie movie = MovieMapper.mapToMovie(movieData);

                        // 중복 데이터 확인 및 한글 제목 유효성 검사
                        if (!movieIds.contains(movie.getId()) && movie.getTitle().matches("^[가-힣0-9\\s\\p{P}]+$")) {
                            movieIds.add(movie.getId()); // 중복 방지를 위해 ID 저장
                            movieList.add(movie);
                        }
                    }

                    page++; // 다음 페이지 요청 준비

                    // 모든 페이지 요청을 완료하면 반복문 탈출
                    if (page >= totalPages) {
                        break;
                    }
                }

                // 로그 출력: 현재 진행 상황 표시 (요청 완료 메시지)
                log.info("[ " + (i + 1) + "/" + (TOTAL_REQUEST_MONTHS / BATCH_REQUEST_MONTHS) + "] TmdbAPI 영화 요청 완료");
            }

            // 수집한 영화 데이터를 DB에 저장
            movieRepository.saveAll(movieList);

        } catch (Exception e) {
            log.error("영화 데이터를 저장하는 중 오류 발생", e);
        }
    }

    public void fetchAndStoreMovie() {
        // 영화 데이터를 저장할 리스트
        List<Movie> movieList = new ArrayList<>();

        // 기존 DB에 저장된 영화 ID 목록을 가져와 중복을 방지
        Set<Long> movieIds = movieRepository.findAllIds();

        try {
            // 요청할 기간을 2개월 단위로 나누어 배열 생성
            String[][] periods = DateUtils.splitMonthsIntoPeriods(12, 2);
            for (int year = 2020; year < 2025; year++) {
                // 나눠진 기간 개수만큼 반복 (예: 6개월 / 2개월 = 3회)
                for (int i = 0; i < 6; i++) {
                    String startDate = Integer.toString(year) + periods[i][0].substring(4); // 시작 날짜
                    String endDate = Integer.toString(year) + periods[i][1].substring(4); // 시작 날짜

                    int page = 1; // 페이지 번호 초기화

                    while (true) { // 페이지네이션 처리
                        // API 요청 URL 생성
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

                        // API 요청 생성
                        Request request = new Request.Builder()
                                .url(url)
                                .get()
                                .addHeader("accept", "application/json")
                                .addHeader("Authorization", "Bearer " + API_KEY)
                                .build();

                        // API 요청 실행 및 응답 받기
                        Response response = client.newCall(request).execute();
                        String responseBody = response.body().string();
                        response.close();

                        // JSON 응답 데이터를 Map 형식으로 변환
                        Map<String, Object> jsonMap = objectMapper.readValue(responseBody, Map.class);
                        List<Map<String, Object>> movies = (List<Map<String, Object>>) jsonMap.get("results");
                        int totalPages = (int) jsonMap.get("total_pages"); // 전체 페이지 수

                        // 응답받은 영화 데이터를 리스트에 추가
                        for (Map<String, Object> movieData : movies) {
                            Movie movie = MovieMapper.mapToMovie(movieData);

                            // 중복 데이터 확인 및 한글 제목 유효성 검사
                            if (!movieIds.contains(movie.getId()) && movie.getTitle().matches("^[가-힣0-9\\s\\p{P}]+$")) {
                                movieIds.add(movie.getId()); // 중복 방지를 위해 ID 저장
                                movieList.add(movie);
                            }
                        }

                        page++; // 다음 페이지 요청 준비

                        // 모든 페이지 요청을 완료하면 반복문 탈출
                        if (page >= totalPages) {
                            break;
                        }
                    }
                    // 로그 출력: 현재 진행 상황 표시 (요청 완료 메시지)
                    log.info("[ " + year + " " + (i + 1) + "/6 ] TmdbAPI 영화 요청 완료");
                }

            }

            // 수집한 영화 데이터를 DB에 저장
            movieRepository.saveAll(movieList);

        } catch (Exception e) {
            log.error("영화 데이터를 저장하는 중 오류 발생", e);
        }
    }

}

