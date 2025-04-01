import React from "react";

export default function MovieReviewSection({reviews}) {
    return (
        <div className="movie-review-container">
            <div className="movie-review-info">
                <div>
                    <h3>리뷰 <span>0</span></h3>
                    <p>평점 : 3.5 / 5</p>
                </div>
                <div>
                    <div className="review-sort-tabs">
                        <button className={active === 'latest' ? 'active' : ''}>최신순</button>
                        <button className={active === 'latest' ? 'active' : ''}>평점 높은순</button>
                        <button className={active === 'latest' ? 'active' : ''}>평점 낮은순</button>
                    </div>
                </div>
            </div>
            <div className="movie-reviews">

            </div>
        </div>
    );
}