import React from "react";
import "./MovieCard.css";

const MovieCard = ({id, title, image_url}) => (
    <a href={`/movies/${id}`}>
        <div className="movie-card">
            <div className="card-content">
                <div className="movieImg">
                    <img src={image_url} alt={title}/>
                </div>
                <div className="title">
                    {title}
                </div>
            </div>
        </div>
    </a>
);
export default MovieCard;