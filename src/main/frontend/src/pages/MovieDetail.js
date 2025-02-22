import React from "react";
import Header from "../components/Header";
import Footer from "../components/Footer";
import "./MovieDetail.css";
import {useEffect, useState} from "react";

// 전체 프론트엔드 레이아웃 (IMDB 사이트 참고하여 기본 HTML 구조 구현)
function MovieDetail() {
    return (
        <div>
            <Header/>
            <main className="main-container">
                <div className="moiveRank">
                </div>
            </main>
            <Footer/>
        </div>
    );
}

export default MovieDetail;
