import React from "react";
import Header from "../components/Common/Header";
import Footer from "../components/Common/Footer";
import "./MovieDetail.css";
import {useEffect, useState} from "react";
import {useParams} from "react-router-dom";
import {fetchMovieById} from "../services/movieService";
import MovieGenre from "../components/Movie/MovieGenre";
import ActorCard from "../components/ActorCard";
import SkeletonCard from "../components/Skeleton";
import {FaLessThan, FaGreaterThan} from "react-icons/fa";
import MovieCastCarousel from "../components/Movie/MovieCastCarousel";
import "lite-youtube-embed/src/lite-yt-embed.css";
import LiteYouTubePlayer from "../components/Movie/LiteYouTubePlayer";
import MovieStillImageCarousel from "../components/Movie/MovieStillImageCarousel";
import MovieReviewSection from "../components/Movie/MovieReviewSection";

// 전체 프론트엔드 레이아웃 (IMDB 사이트 참고하여 기본 HTML 구조 구현)
function MovieDetail() {
    const {id} = useParams();
    const [movie, setMovie] = useState([]);
    const [isLoading, setIsLoading] = useState(true);

    useEffect(() => {
        fetchMovieById(id).then((data) => {
            setMovie(data);
        }).catch((error) => {
            console.error(error);
        }).finally(() => setIsLoading(false))
    }, [id]);

    console.log(movie);
    if (isLoading) {
        return (
            <div>
                <Header/>
                <main className="main-container">
                    <div className="main-content">
                        <div className="skeleton-container">
                            <div className="skeleton-header">
                                <SkeletonCard width="290" height="50"/>
                                <SkeletonCard width="290" height="15"/>
                            </div>
                            <div className="skeleton-content">
                                <SkeletonCard width="300" height="350"/>
                                <div className="skeleton-infomation">
                                    <SkeletonCard width="900" height="215"/>
                                    <SkeletonCard width="900" height="120"/>
                                </div>
                            </div>
                            <div className="skeleton-casts">
                                <SkeletonCard width="1200" height="225"/>
                                <SkeletonCard width="1200" height="500"/>
                            </div>
                        </div>
                    </div>
                </main>
                <Footer/>
            </div>
        );
    } else {
        return (
            <div>
                <Header/>
                <main className="main-container">
                    <div className="main-content">
                        <div className="movie-header">
                            <div className="movie-summary">
                                <h1 className="movie-title">{movie.title}</h1>
                                <span className="movie-original-title">{movie.original_title}</span>
                                <span className="movie-info">{movie.release_date}</span>
                            </div>
                            <div className="movie-rating">{movie.rating}</div>
                        </div>
                        <div className="movie-content">
                            <img className="movie-poster" src={movie.poster_path} alt={movie.title}/>
                            <div className="movie-infomation">
                                <div className="movie-genres">
                                    {movie.genres.map((genre) => (
                                        <MovieGenre key={genre.id} id={genre.id} name={genre.name}/>
                                    ))}
                                </div>
                                <hr/>
                                <h3>║줄거리</h3>
                                <p className="movie-overview">{movie.overview ? (
                                    movie.overview
                                ) : (
                                    "줄거리 정보가 제공되지 않았습니다."
                                )}</p>
                                <hr/>
                                <div className="rating-container">

                                    <div className="rating-box">
                                        <span className="rating-label">IMDb RATING</span>
                                        <div className="rating-value">
                                            <span className="star-icon">⭐</span>
                                            <span className="rating-score">6.1</span><span
                                            className="rating-out-of">/10</span>
                                        </div>
                                    </div>

                                    <div className="rating-box">
                                        <span className="rating-label">YOUR RATING</span>
                                        <div className="rating-value">
                                            <span className="rate-icon">⭐</span>
                                            <span className="rate-text">Rate</span>
                                        </div>
                                    </div>

                                    <div className="rating-box">
                                        <span className="rating-label">POPULARITY</span>
                                        <div className="rating-value">
                                            <span className="popularity-icon">🔥</span>
                                            <span className="popularity-rank">2</span>
                                            <span className="popularity-change">-1</span>
                                        </div>
                                    </div>
                                </div>
                                <hr/>
                            </div>
                        </div>

                        <div className="movie-content-container">
                            <div className="movie-actor-container">
                                <h2 style={{color: "#ffffff", marginBottom: 25}}>║출연진</h2>
                                <MovieCastCarousel key={movie.id} casts={movie.credits.cast}/>
                            </div>
                            <h2 style={{color: "#ffffff", marginTop: 50, marginBottom: 25}}>║트레일러</h2>
                            <div className="movie-trailer-container">
                                {movie.videos?.results && movie.videos.results.length > 0 ? (
                                    movie.videos.results.slice(0, 2).map((trailer) => (
                                        <LiteYouTubePlayer key={trailer.key} data={trailer}/>
                                    ))
                                ) : (
                                    <h1 style={{color: '#ffffff'}}>트레일러 정보가 제공되지 않았습니다.</h1>
                                )}
                            </div>
                            <h2 style={{color: "#ffffff", marginTop: 50, marginBottom: 25}}>║스틸컷</h2>
                            <MovieStillImageCarousel key={movie.id} images={movie.images.backdrops}/>
                            {/*<MovieReviewSection key={movie.id} reviews={movie.reviews}/>*/}
                        </div>
                    </div>
                </main>
                <Footer/>
            </div>
        );
    }
}

export default MovieDetail;

