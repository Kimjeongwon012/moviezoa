// src/components/Pagination.js
import React from "react";
import "./Pagination.css";

const Pagination = ({currentPage, totalPages, onPageChange}) => {
    const pageNumbers = [];

    for (let i = 1; i <= totalPages; i++) {
        pageNumbers.push(i);
    }

    return (
        <div className="pagination">
            <button
                onClick={() => onPageChange(currentPage - 1)}
                disabled={currentPage === 1}
                className="page-button"
            >
                이전
            </button>

            {pageNumbers.map((number) => (
                <button
                    key={number}
                    onClick={() => onPageChange(number)}
                    className={`page-number ${
                        number === currentPage ? "active" : ""
                    }`}
                >
                    {number}
                </button>
            ))}

            <button
                onClick={() => onPageChange(currentPage + 1)}
                disabled={currentPage === totalPages}
                className="page-button"
            >
                다음
            </button>
        </div>
    );
};

export default Pagination;
