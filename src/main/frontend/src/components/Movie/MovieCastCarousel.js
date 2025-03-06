import React from "react";
import {useEffect, useState} from "react";
import "./MovieCastCarousel.css";
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
    
    return (
        <div className="movie-actor-carousel-container">
            <div className="movie-actor-carousel-button-left">
                <FaLessThan style={{fontSize: 25, color: "#ffffff"}}
                            onClick={handleBtnLeftClick}/>
            </div>
            <div className="movie-actor-carousel">
                <div className="movie-actor-list"
                     style={{transform: `translateX(${scrollX}px)`, width: casts.length * cardWidth}}>
                    {casts.map((cast) => (
                        <ActorCard key={cast.id} id={cast.id} name={cast.name}
                                   character={cast.character}
                                   profilePath={cast.profile_path}
                                   cardWidth={cardWidth}/>
                    ))}
                </div>
            </div>
            <div className="movie-actor-carousel-button-right">
                <FaGreaterThan style={{fontSize: 25, color: "#ffffff"}}
                               onClick={handleBtnRightClick}/>
            </div>
        </div>
    );
}

export default MovieCastCarousel;