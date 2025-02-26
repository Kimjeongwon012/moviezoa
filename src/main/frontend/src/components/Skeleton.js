import React from "react";
import "./Skeleton.css";

const SkeletonCard = ({width, height}) => {
    return (
        <div className="skeleton skeleton-image" style={{
            width: `${width}px`,
            height: `${height}px`
        }}></div>
    );
};

export default SkeletonCard;
