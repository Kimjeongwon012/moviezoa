import React, {useState, useEffect} from "react";
import "./MovieReviewSecetion.css";
import {fetchReviewsByMovieId} from "../../services/movieService";

export default function MovieReviewSection({movieId}) {
    const [active, setActive] = useState('');
    const [review, setReview] = useState({reviews: [], avgRating: 0, length: 0});

    useEffect(() => {
        fetchReviewsByMovieId(movieId).then((data) => {
            setReview(data);
        }).catch((error) => {
            console.error(error);
        });
    }, [active]);

    console.log(review);
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
                    <div className="review-card" key={item.id}>
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