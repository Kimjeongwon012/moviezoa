import React from "react";
import "./MovieGenre.css";

const MovieGenre = ({id, name}) => (
    <a href={`/movies/genre/${id}`}>
        <button className="genre-button">{name}</button>
    </a>
);

export default MovieGenre;