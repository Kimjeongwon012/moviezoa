import React from "react";
import styles from "./MovieGenre.module.css";
import {Link} from 'react-router-dom';

const MovieGenre = ({id, name}) => (
    <Link to={`/movie/genre/${id}`}>
        <button className={styles.genreButton}>{name}</button>
    </Link>
);

export default MovieGenre;