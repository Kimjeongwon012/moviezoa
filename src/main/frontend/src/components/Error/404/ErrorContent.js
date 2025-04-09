import React from "react";
import styles from "../../../pages/NotFound.module.css";

import ErrorMessage from "./ErrorMessage";

function ErrorContent() {
    return (
        <div className={styles.contentLayout}>
            <div className={styles.imageColumn}>
                <img
                    src="https://cdn.builder.io/api/v1/image/assets/TEMP/df90a559a1752a2c9a61884cedc231650c95e09e?placeholderIfAbsent=true&apiKey=d0ff6b38104642468674b5bc132ceb0e"
                    alt="404 illustration" className={styles.mainImage}/>
            </div>
            <div className={styles.messageColumn}>
                <ErrorMessage/>
            </div>
        </div>
    );
}

export default ErrorContent;
