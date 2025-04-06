import React, {useState, useEffect} from "react";
import "./MovieReviewSecetion.css";
import {fetchReviews, postReview} from "../../services/movieService";
import Pagination from "../Common/Pagination";

export default function MovieReviewSection({movieId}) {
    const [onSort, setOnSort] = useState('createdAt,desc');
    const [currentPage, setCurrentPage] = useState(1);
    const [review, setReview] = useState({reviews: [], avgRating: 0, length: 0});
    const [content, setContent] = useState('');
    const [rating, setRating] = useState(0);
    const [reloadTrigger, setReloadTrigger] = useState(false);
    const pageSize = 5;

    useEffect(() => {
        fetchReviews(movieId, onSort, currentPage, pageSize).then((data) => {
            setReview(data);
        }).catch((error) => {
            console.error(error);
        });
    }, [onSort, reloadTrigger]);

    function onPageChange(pageNumber) {
        setCurrentPage(pageNumber);
        setReloadTrigger(prev => !prev);
    }

    const handleSubmit = async (e) => {
        e.preventDefault(); // form의 기본 동작(새로고침) 방지
        if (content === '') {
            alert('리뷰 내용을 작성해주세요');
        } else if (rating === 0) {
            alert('별점을 부여해주세요');
        }
        await postReview(movieId, content, rating);
        setRating(0);
        setContent('');
        setReloadTrigger(prev => !prev);
    };

    return (
        <div className="movie-review-container">
            <div className="movie-review-info">
                <div>
                    <h3>리뷰<span style={{fontSize: 21}}>&nbsp;{review.totalCount}</span></h3>
                    <p>평점 : &nbsp;<span>{review.avgRating.toFixed(1)}</span> / 5</p>
                </div>
                <div>
                    <div className="review-sort-tabs">
                        <button onClick={() => setOnSort('createdAt,desc')}
                                className={onSort === 'createdAt,desc' ? 'active' : ''}>최신순
                        </button>
                        <button onClick={() => setOnSort('rating,desc')}
                                className={onSort === 'rating,desc' ? 'active' : ''}>평점 높은순
                        </button>
                        <button onClick={() => setOnSort('rating,asc')}
                                className={onSort === 'rating,asc' ? 'active' : ''}>평점 낮은순
                        </button>
                    </div>
                </div>
            </div>
            <div className="comment-write">
                <div className="comment-header">
                    <h3>김정원</h3>
                </div>
                <form className="comment-form" onSubmit={handleSubmit}>
                    <textarea className="comment-input"
                              placeholder="리뷰를 입력해주세요..."
                              value={content}
                              onChange={(e) => setContent(e.target.value)}/>
                    <div className="comment-star-button">
                        <div className="star-rating">
                            {Array.from({length: 5}, (_, i) => (
                                <span
                                    key={i}
                                    className={`star ${i < rating ? "filled" : ""}`}
                                    onClick={() => setRating(i + 1)}
                                >★</span>
                            ))}
                        </div>
                        <button type="submit" className="comment-submit">댓글 등록</button>
                    </div>
                </form>
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
            <div className="comment-nav">
                <Pagination currentPage={currentPage} totalPages={Math.ceil(review.totalCount / pageSize)}
                            onPageChange={onPageChange}/>
            </div>
        </div>
    );
}