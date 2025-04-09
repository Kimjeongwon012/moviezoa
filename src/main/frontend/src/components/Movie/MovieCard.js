import React from "react";
import "./MovieCard.css";
import {Link} from 'react-router-dom';

const MovieCard = ({id, title, image_url}) => (
    <Link to={`/movies/${id}`}>
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
    </Link>
);
export default MovieCard;