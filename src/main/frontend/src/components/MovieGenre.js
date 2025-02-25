import React from "react";
import "./MovieGenre.css";

const MovieGenre = ({genre}) => (
    <a href={`/movies/genre/${genre}`}>
        <button className="genre-button">{genre}</button>
    </a>
);

export default MovieGenre;