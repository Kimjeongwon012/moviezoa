import React from "react";
import styles from "../../../pages/NotFound.module.css";

import {Link} from 'react-router-dom';

function ErrorMessage() {
    return (
        <div className={styles.messageContainer}>
            <h1 className={styles.errorCode}>404</h1>
            <h2 className={styles.errorTitle}>요청하신 페이지는</h2>
            <h2 className={styles.errorTitle}>없는 페이지입니다!</h2>
            <Link to="/" className={styles.homeLink}>시작 페이지로 이동합니다
            </Link>
        </div>
    );
}

export default ErrorMessage;
