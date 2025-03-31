import React from "react";
import {useEffect, useState} from "react";
import "./MovieStillImageCarousel.css";
import {FaGreaterThan, FaLessThan} from "react-icons/fa";

function MovieStillImageCarousel({images}) {
    const [imageIndex, setImagesIndex] = useState(0);
    const MAX_STILL_COUNT = images.length;

    const handleBtnLeftClick = (e) => {
        let x = imageIndex - 1;
        if (x < 0) {
            x = 0;
        }
        setImagesIndex(x);
    };

    const handleBtnRightClick = (e) => {
        let x = imageIndex + 1;
        if (x >= MAX_STILL_COUNT) {
            x = 0;
        }
        setImagesIndex(x);
    };

    if (images.length == 0) {
        return (
            <div className="movie-still-iamge-carousel-container" style={{justifyContent: 'center'}}>
                <h1 style={{color: '#ffffff'}}>스틸컷 정보가 제공되지 않았습니다.</h1>
            </div>
        );
    } else {
        return (
            <div className="movie-still-iamge-carousel-container">
                <div className="movie-still-iamge-carousel-button-left">
                    <FaLessThan style={{fontSize: 45, color: "#ffffff"}}
                                onClick={handleBtnLeftClick}/>
                </div>
                <div className="movie-still-iamge-carousel">
                    <img src={images[imageIndex].file_path} style={{width: 800}}/>
                </div>
                <div className="movie-still-iamge-carousel-button-right">
                    <FaGreaterThan style={{fontSize: 45, color: "#ffffff"}}
                                   onClick={handleBtnRightClick}/>
                </div>
            </div>
        );
    }
}

export default MovieStillImageCarousel;