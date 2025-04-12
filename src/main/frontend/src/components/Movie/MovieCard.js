import React from "react";
import styles from "./MovieCard.module.css";
import {Link} from 'react-router-dom';

const MovieCard = ({id, title, image_url}) => (
    <Link to={`/movie/${id}`}>
        <div className={styles.movieCard}>
            <div className={styles.cardContent}>
                <div>
                    <img src={image_url} alt={title}/>
                </div>
            </div>
        </div>
    </Link>
);
export default MovieCard;