import React from "react";
import "./ActorCard.css";

export default function ActorCard({id, name, character, profilePath, cardWidth}) {
    return (
        <a href={`/movies/credit/${id}`}>
            <div className="actor-card-container" style={{maxWidth: cardWidth}}>
                <div className="actor-image">
                    <img width="120px" height="120px" src={profilePath} alt={name}/>
                </div>
                <div className="actor-info">
                    <div className="actor-name">
                        <span>{name}</span>
                    </div>
                    <div className="actor-role">
                        <span>{character}</span>
                    </div>
                </div>
            </div>
        </a>
    );
}