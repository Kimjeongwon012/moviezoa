import React from "react";
import {Link} from 'react-router-dom';
import styles from "./ActorCard.module.css";

export default function ActorCard({id, name, character, profilePath, cardWidth}) {
    return (
        <Link to={`/movies/credit/${id}`}>
            <div className={styles.container} style={{maxWidth: cardWidth}}>
                <div>
                    <img className={styles.img} src={profilePath} alt={name}/>
                </div>
                <div className={styles.info}>
                    <div className={styles.name}>
                        <span>{name}</span>
                    </div>
                    {/*<div className={styles.role}>*/}
                    {/*    <span>{character}</span>*/}
                    {/*</div>*/}
                </div>
            </div>
        </Link>
    );
}