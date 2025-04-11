// MovieDetailPage.js
import React from "react";
import Header from "../components/Common/Header";
import Footer from "../components/Common/Footer";
import styles from "./MovieDetail.module.css";
import {useEffect, useState} from "react";
import {useParams} from "react-router-dom";
import {fetchMovieById} from "../services/movieService";
import MovieGenre from "../components/Movie/MovieGenre";
import SkeletonCard from "../components/Skeleton";
import MovieCastCarousel from "../components/Movie/MovieCastCarousel";
import "lite-youtube-embed/src/lite-yt-embed.css";
import LiteYouTubePlayer from "../components/Movie/LiteYouTubePlayer";
import MovieStillImageCarousel from "../components/Movie/MovieStillImageCarousel";
import MovieReviewSection from "../components/Movie/MovieReviewSection";

function MovieDetailPage() {
    const {id} = useParams();
    const [movie, setMovie] = useState([]);
    const [isLoading, setIsLoading] = useState(true);

    useEffect(() => {
        fetchMovieById(id).then((data) => {
            setMovie(data);
        }).catch((error) => {
            console.error(error);
        }).finally(() => setIsLoading(true))
    }, [id]);

    console.log(movie);
    if (isLoading) {
        return (
            <div>
                <Header/>
                <main className={styles.mainContainer}>
                    <div className={styles.mainContent}>
                        <div className={styles.skeletonWrapper}>
                            <div className={styles.skeletonTitleSection}>
                                <SkeletonCard width="290" height="50"/>
                                <SkeletonCard width="290" height="15"/>
                            </div>
                            <div className={styles.skeletonMainSection}>
                                <SkeletonCard width="300" height="350"/>
                                <div className={styles.skeletonInfoBlock}>
                                    <SkeletonCard width="900" height="215"/>
                                    <SkeletonCard width="900" height="120"/>
                                </div>
                            </div>
                            <div className={styles.skeletonCastSection}>
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
                <main className={styles.mainContainer}>
                    <div className={styles.mainContent}>
                        <div className={styles.detailHeader}>
                            <div className={styles.detailTitleBox}>
                                <h1 className={styles.detailTitle}>{movie.title}</h1>
                                <span className={styles.detailOriginalTitle}>{movie.original_title}</span>
                                <span className={styles.detailReleaseDate}>{movie.release_date}</span>
                            </div>
                            <div className={styles.detailRatingBox}>{movie.rating}</div>
                        </div>
                        <div className={styles.detailMainContent}>
                            <img className={styles.detailPoster} src={movie.poster_path} alt={movie.title}/>
                            <div className={styles.detailInfoBox}>
                                <div className={styles.detailGenreList}>
                                    {movie.genres.map((genre) => (
                                        <MovieGenre key={genre.id} id={genre.id} name={genre.name}/>
                                    ))}
                                </div>
                                <hr/>
                                <h3>║줄거리</h3>
                                <p className={styles.detailOverview}>{movie.overview || "줄거리 정보가 제공되지 않았습니다."}</p>
                                <hr/>
                                <div className={styles.ratingSection}>
                                    <div className={styles.ratingCard}>
                                        <span className={styles.ratingTitle}>IMDb RATING</span>
                                        <div className={styles.ratingValueBox}>
                                            <span className={styles.ratingStar}>⭐</span>
                                            <span className={styles.ratingScoreValue}>6.1</span>
                                            <span className={styles.ratingOutof}>/10</span>
                                        </div>
                                    </div>
                                    <div className={styles.ratingCard}>
                                        <span className={styles.ratingTitle}>YOUR RATING</span>
                                        <div className={styles.ratingValueBox}>
                                            <span className={styles.ratingUserStar}>⭐</span>
                                            <span className={styles.ratingUserAction}>Rate</span>
                                        </div>
                                    </div>
                                    <div className={styles.ratingCard}>
                                        <span className={styles.ratingTitle}>POPULARITY</span>
                                        <div className={styles.ratingValueBox}>
                                            <span className={styles.ratingPopularityIcon}>🔥</span>
                                            <span className={styles.ratingPopularityRank}>2</span>
                                            <span className={styles.ratingPopularityDiff}>-1</span>
                                        </div>
                                    </div>
                                </div>
                                <hr/>
                            </div>
                        </div>
                        <div className={styles.detailExtraContainer}>
                            <div className={styles.detailCastBox}>
                                <h2 style={{marginBottom: 25}}>║출연진</h2>
                                <MovieCastCarousel key={movie.id} casts={movie.credits.cast}/>
                            </div>
                            <h2 style={{marginTop: 50, marginBottom: 25}}>║트레일러</h2>
                            <div className={styles.detailTrailerBox}>
                                {movie.videos?.results?.length > 0 ? (
                                    movie.videos.results.slice(0, 2).map((trailer) => (
                                        <LiteYouTubePlayer key={trailer.key} data={trailer}/>
                                    ))
                                ) : (
                                    <h1>트레일러 정보가 제공되지 않았습니다.</h1>
                                )}
                            </div>
                            <h2 style={{marginTop: 50, marginBottom: 25}}>║스틸컷</h2>
                            <MovieStillImageCarousel key={movie.id} images={movie.images.backdrops}/>
                            <MovieReviewSection key={movie.id + 'review'} movieId={movie.id}/>
                        </div>
                    </div>
                </main>
                <Footer/>
            </div>
        );
    }
}

export default MovieDetailPage;