import React from "react";
import Header from "../components/Header";
import Footer from "../components/Footer";
import "./MovieDetail.css";
import {useEffect, useState} from "react";
import {useParams} from "react-router-dom";
import {fetchMovieById} from "../services/movieService";

// 전체 프론트엔드 레이아웃 (IMDB 사이트 참고하여 기본 HTML 구조 구현)
function MovieDetail() {
    const {id} = useParams();
    const [movie, setMovie] = useState([]);

    useEffect(() => {
        fetchMovieById(id).then((data) => setMovie(data)).catch(console.error);
    }, [id]);

    return (
        <div>
            <Header/>
            <main className="main-container">
                <div className="movie-header">
                    <div className="movie-summary">
                        <h1 className="movie-title">{movie.title}</h1>
                        <span className="movie-original-title">{movie.original_title}</span>
                        <span className="movie-info">{movie.release_date}</span>
                    </div>
                    <div className="movie-rating">
                        {movie.rating}
                    </div>
                </div>
                {/*포스터*/}
                <div className="movie-content">


                </div>
                {/*영화 정보*/}
                <div className="movie-info-seection">

                </div>
            </main>
            <Footer/>
        </div>
    );
}

export default MovieDetail;
