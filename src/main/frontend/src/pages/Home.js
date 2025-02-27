import React from "react";
import Header from "../components/Common/Header";
import Footer from "../components/Common/Footer";
import "./Home.css";
import MovieCard from "../components/Movie/MovieCard";
import {fetchMostPopularMovies} from "../services/movieService";
import {useEffect, useState} from "react";

// 전체 프론트엔드 레이아웃 (IMDB 사이트 참고하여 기본 HTML 구조 구현)
function Home() {
    const [movies, setMovies] = useState([]);

    useEffect(() => {
        // movieService.js 의 fetchMovies 를 실행하고 백엔드로부터 받아온 데이터를 movies 에 저장, 오류시 발생시 콘솔에 출력
        fetchMostPopularMovies(25).then((data) => setMovies(data)).catch(console.error);
    }, []);

    return (
        <div>
            <Header/>
            <main className="main-container">
                <div className="main-content">
                    <div className="moive-rank">
                        <h3>금주의 영화</h3>
                        <div className="movieList">
                            {movies.map((movie) => (
                                <MovieCard key={movie.id} id={movie.id} title={movie.title}
                                           image_url={movie.poster_path}/>
                            ))}
                        </div>
                    </div>
                </div>
            </main>
            <Footer/>
        </div>
    );
}

export default Home;
