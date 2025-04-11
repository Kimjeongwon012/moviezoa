import React from "react";
import styles from "./Skeleton.module.css";

const SkeletonCard = ({width, height}) => {
    return (
        <div
            className={`${styles.skeleton} ${styles.skeletonImage}`}
            style={{width: `${width}px`, height: `${height}px`}}
        ></div>
    );
};

export default SkeletonCard;