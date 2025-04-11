import React from "react";
import styles from "./Pagination.module.css";

const Pagination = ({currentPage, totalPages, onPageChange}) => {
    const pageNumbers = [];

    for (let i = 1; i <= totalPages; i++) {
        pageNumbers.push(i);
    }

    return (
        <div className={styles.pagination}>
            <button
                onClick={() => onPageChange(currentPage - 1)}
                disabled={currentPage === 1}
                className={styles.pageButton}
            >
                이전
            </button>

            {pageNumbers.map((number) => (
                <button
                    key={number}
                    onClick={() => onPageChange(number)}
                    className={`${styles.pageNumber} ${number === currentPage ? styles.active : ""}`}
                >
                    {number}
                </button>
            ))}

            <button
                onClick={() => onPageChange(currentPage + 1)}
                disabled={currentPage === totalPages}
                className={styles.pageButton}
            >
                다음
            </button>
        </div>
    );
};

export default Pagination;