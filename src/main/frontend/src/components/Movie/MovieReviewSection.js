import React, {useState, useEffect} from "react";
import styles from "./MovieReviewSection.module.css";
import {fetchReviews, postReview} from "../../services/movieService";
import Pagination from "../Common/Pagination";

export default function MovieReviewSection({movieId}) {
    const [sortOption, setSortOption] = useState('createdAt,desc');
    const [currentPage, setCurrentPage] = useState(1);
    const [reviewData, setReviewData] = useState({reviews: [], avgRating: 0, totalCount: 0});
    const [content, setContent] = useState('');
    const [rating, setRating] = useState(0);
    const [reloadTrigger, setReloadTrigger] = useState(false);
    const pageSize = 5;

    useEffect(() => {
        fetchReviews(movieId, sortOption, currentPage, pageSize).then((data) => {
            setReviewData(data);
        }).catch((error) => {
            console.error(error);
        });
    }, [sortOption, reloadTrigger, currentPage]);

    function onPageChange(pageNumber) {
        setCurrentPage(pageNumber);
    }

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (content === '') {
            alert('리뷰 내용을 작성해주세요');
            return;
        } else if (rating === 0) {
            alert('별점을 부여해주세요');
            return;
        }
        await postReview(movieId, content, rating);
        setRating(0);
        setContent('');
        setReloadTrigger(prev => !prev);
    };

    return (
        <div className={styles.reviewContainer}>
            <div className={styles.headerSection}>
                <div>
                    <h3>리뷰<span style={{fontSize: 21}}>&nbsp;{reviewData.totalCount}</span></h3>
                    <p>평점 : &nbsp;<span>{(reviewData.avgRating ?? 0).toFixed(1)}</span> / 5</p>
                </div>
                <div className={styles.sortTabs}>
                    <button onClick={() => setSortOption('createdAt,desc')}
                            className={sortOption === 'createdAt,desc' ? 'active' : ''}>최신순
                    </button>
                    <button onClick={() => setSortOption('rating,desc')}
                            className={sortOption === 'rating,desc' ? 'active' : ''}>평점 높은순
                    </button>
                    <button onClick={() => setSortOption('rating,asc')}
                            className={sortOption === 'rating,asc' ? 'active' : ''}>평점 낮은순
                    </button>
                </div>
            </div>

            <div className={styles.writeSection}>
                <div className={styles.writerProfile}><h3>김정원</h3></div>
                <form className={styles.reviewForm} onSubmit={handleSubmit}>
                    <textarea className={styles.textarea} placeholder="리뷰를 입력해주세요..." value={content}
                              onChange={(e) => setContent(e.target.value)}/>
                    <div className={styles.formFooter}>
                        <div className={styles.starRating}>
                            {Array.from({length: 5}, (_, i) => (
                                <span
                                    key={i}
                                    className={`${styles.star} ${i < rating ? styles.filled : ''}`}
                                    onClick={() => setRating(i + 1)}>
                                    ★
                                </span>
                            ))}
                        </div>
                        <button type="submit" className={styles.submitButton}>댓글 등록</button>
                    </div>
                </form>
            </div>

            <div className={styles.reviewList}>
                {reviewData.reviews.map((item) => (
                    <div className={styles.reviewCard} key={item.id}>
                        <div className={styles.cardHeader}>
                            <h3>김정원</h3>
                            <div className={styles.cardStars}>
                                {Array.from({length: 5}).map((_, idx) => (
                                    <span key={idx}
                                          className={idx < item.rating ? `${styles.star} ${styles.filled}` : styles.star}>★</span>
                                ))}
                            </div>
                        </div>
                        <div className={styles.cardContent}>
                            <h4>{item.content}</h4>
                        </div>
                    </div>
                ))}
            </div>

            <div className={styles.paginationWrapper}>
                <Pagination
                    currentPage={currentPage}
                    totalPages={Math.ceil(reviewData.totalCount / pageSize)}
                    onPageChange={onPageChange}
                />
            </div>
        </div>
    );
}