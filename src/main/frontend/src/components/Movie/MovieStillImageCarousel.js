// CarouselStillImage.js
import React from "react";
import {useState} from "react";
import styles from "./MovieCastCarousel.module.css";
import {FaGreaterThan, FaLessThan} from "react-icons/fa";

function CarouselStillImage({images}) {
    const [imageIndex, setImageIndex] = useState(0);
    const MAX_STILL_COUNT = images.length;

    const handleBtnLeftClick = () => {
        let x = imageIndex - 1;
        if (x < 0) x = 0;
        setImageIndex(x);
    };

    const handleBtnRightClick = () => {
        let x = imageIndex + 1;
        if (x >= MAX_STILL_COUNT) x = 0;
        setImageIndex(x);
    };

    if (images.length === 0) {
        return (
            <div className={styles.container} style={{justifyContent: 'center'}}>
                <h1 style={{color: '#ffffff'}}>스틸컷 정보가 제공되지 않았습니다.</h1>
            </div>
        );
    } else {
        return (
            <div className={styles.container}>
                <div className={styles.carouselBtnLeft}>
                    <FaLessThan style={{fontSize: 45, color: "#ffffff"}} onClick={handleBtnLeftClick}/>
                </div>
                <div className={styles.carouselImageWrapper}>
                    <img src={images[imageIndex].file_path} className={styles.carouselImage} alt="스틸컷"/>
                </div>
                <div className={styles.carouselBtnRight}>
                    <FaGreaterThan style={{fontSize: 45, color: "#ffffff"}} onClick={handleBtnRightClick}/>
                </div>
            </div>
        );
    }
}

export default CarouselStillImage;
