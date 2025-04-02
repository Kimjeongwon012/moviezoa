import React, {useState} from "react";
import "./MovieReviewSecetion.css";

export default function MovieReviewSection({review}) {
    const [active, setActive] = useState('');
    console.log('test', review);
    return (
        <div className="movie-review-container">
            <div className="movie-review-info">
                <div>
                    <h3>리뷰<span style={{fontSize: 21}}>&nbsp;{review.length}</span></h3>
                    <p>평점 : &nbsp;<span>{review.avgRating}</span> / 5</p>
                </div>
                <div>
                    <div className="review-sort-tabs">
                        <button onClick={() => setActive('latest')}
                                className={active === 'latest' ? 'active' : ''}>최신순
                        </button>
                        <button onClick={() => setActive('highRating')}
                                className={active === 'highRating' ? 'active' : ''}>평점 높은순
                        </button>
                        <button onClick={() => setActive('lowRating')}
                                className={active === 'lowRating' ? 'active' : ''}>평점 낮은순
                        </button>
                    </div>
                </div>
            </div>
            <div className="movie-reviews">
                {review.reviews.map((item) => (
                    <div className="review-card">
                        <div className="review-header">
                            <h3>김정원</h3>
                            <div className="review-star">
                                {Array.from({length: 5}).map((_, idx) => (
                                    <span key={idx} className={idx < item.rating ? 'star filled' : 'star'}>★</span>
                                ))}
                            </div>
                        </div>
                        <div className="review-content">
                            <h4>{item.content}</h4>
                        </div>
                    </div>

                ))}
            </div>
        </div>
    );
}