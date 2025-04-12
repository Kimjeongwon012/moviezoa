import React from "react";
import {useState} from "react";
import styles from "./MovieCastCarousel.module.css";
import {FaGreaterThan, FaLessThan} from "react-icons/fa";
import ActorCard from "../ActorCard";

function MovieCastCarousel({casts}) {
    const [scrollX, setScrollX] = useState(0);
    const cardWidth = 165;

    const handleBtnLeftClick = (e) => {
        let x = scrollX + Math.round(window.innerWidth / 2);
        console.log(window.innerWidth, x);
        if (x > 0) {
            x = 0;
        }
        setScrollX(x);
    };

    const handleBtnRightClick = (e) => {
        let x = scrollX - Math.round(window.innerWidth / 2);
        let end = casts.length * cardWidth;
        console.log(window.innerWidth, x);
        if (x * (-1) > end) {
            x = scrollX;
        }
        setScrollX(x);
    };
    if (casts.length == 0) {
        return (
            <div className={styles.container} style={{justifyContent: 'center'}}>
                <h1>출연진 정보가 제공되지 않았습니다.</h1>
            </div>
        );
    } else {
        return (
            <div className={styles.container}>
                <div>
                    <FaLessThan style={{fontSize: 25}}
                                onClick={handleBtnLeftClick}/>
                </div>
                <div className={styles.carousel}>
                    <div className={styles.actorList}
                         style={{transform: `translateX(${scrollX}px)`, width: casts.length * cardWidth}}>
                        {casts.map((cast) => (
                            <ActorCard key={cast.id} id={cast.id} name={cast.name}
                                       character={cast.character}
                                       profilePath={cast.profile_path}
                                       cardWidth={cardWidth}/>
                        ))}
                    </div>
                </div>
                <div>
                    <FaGreaterThan style={{fontSize: 25}}
                                   onClick={handleBtnRightClick}/>
                </div>
            </div>
        );
    }
}

export default MovieCastCarousel;