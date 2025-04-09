import React from "react";
import Header from "../components/Common/Header";
import Footer from "../components/Common/Footer";
import {Link} from 'react-router-dom';

import styles from "./NotFound.module.css";
import ErrorContent from "../components/Error/404/ErrorContent";
import ErrorImage from "../components/Error/404/ErrorImage";


function NotFound() {
    return (
        <main className={styles.pageContainer}>
            <div className={styles.contentWrapper}>
                <section className={styles.mainColumn}>
                    <div className={styles.innerContent}>
                        <ErrorContent/>
                    </div>
                </section>
                <aside className={styles.sideColumn}>
                    <ErrorImage
                        src="https://cdn.builder.io/api/v1/image/assets/TEMP/f1d0340a405d181ce44d9d594235c4ed12c2bdd1?placeholderIfAbsent=true&apiKey=d0ff6b38104642468674b5bc132ceb0e"
                        className={styles.sideImage}/>
                </aside>
            </div>
        </main>
    );
}

export default NotFound;