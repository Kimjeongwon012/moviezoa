import React from "react";
import Header from "../components/Header";
import Footer from "../components/Footer";
import "./MovieDetail.css";
import {useEffect, useState} from "react";
import {useParams} from "react-router-dom";
import {fetchMovieById} from "../services/movieService";
import MovieGenre from "../components/MovieGenre";
import ActorCard from "../components/ActorCard";

// 전체 프론트엔드 레이아웃 (IMDB 사이트 참고하여 기본 HTML 구조 구현)
function MovieDetail() {
    const {id} = useParams();
    const [movie, setMovie] = useState([]);

    useEffect(() => {
        fetchMovieById(id).then((data) => setMovie(data)).catch(console.error);
    }, [id]);
    console.log(movie);

    return (
        <div>
            <Header/>
            <main className="main-container">
                <div className="main-content">
                    <div className="movie-header">
                        <div className="movie-summary">
                            {/*제목*/}
                            <h1 className="movie-title">{movie.title}</h1>
                            {/*원제*/}
                            <span className="movie-original-title">{movie.original_title}</span>
                            {/*개봉일*/}
                            <span className="movie-info">{movie.release_date}</span>
                        </div>
                        <div className="movie-rating">
                            {movie.rating}
                        </div>
                    </div>
                    <div className="movie-content">
                        {/*포스터*/}
                        <img className="movie-poster" src={movie.poster_path} alt={movie.title}/>
                        <div className="movie-infomation">
                            {/*장르 태그*/}
                            <div className="movie-genre">
                                <MovieGenre genre="액션"/>
                            </div>
                            <hr/>
                            {/*영화 시놉시스*/}
                            <p className="movie-overview">
                                {movie.overview}
                            </p>
                            <hr/>
                            {/*출연진*/}
                            <div className="movie-actors">
                                <ActorCard key={movie.id}/>
                            </div>
                            <hr/>
                        </div>
                    </div>
                    {/*영화 정보*/}
                    <div className="movie-info-seection">

                    </div>
                </div>
            </main>
            <Footer/>
        </div>
    );
}

export default MovieDetail;
