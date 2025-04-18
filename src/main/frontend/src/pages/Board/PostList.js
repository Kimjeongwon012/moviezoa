import React from "react";
import Header from "../../components/Common/Header";
import Footer from "../../components/Common/Footer";

import styles from "./PostList.module.css";
import {Link, useParams} from 'react-router-dom';
import {useEffect, useState} from "react";
import {fetchPostsByBoardId} from "../../services/movieService";

function PostList() {
    const {id} = useParams();
    const [board, setBoard] = useState([]);

    const [sortOption, setSortOption] = useState('createdAt,desc');
    const [currentPage, setCurrentPage] = useState(1);
    const [reloadTrigger, setReloadTrigger] = useState(false);
    const pageSize = 5;

    useEffect(() => {
        fetchPostsByBoardId(id, sortOption, currentPage, pageSize).then((data) => {
            setBoard(data);
        }).catch((error) => {
            console.error(error);
        });
    }, [reloadTrigger]);

    console.log(board);

    return (
        <div>
            <Header/>
            <main className={styles.mainContainer}>
                <div className={styles.mainContent}>
                    <div className={styles.header}>
                        <h1>{board.name}</h1>
                    </div>
                    <div className={styles.posts}>
                        <table>
                            <thead>
                            <tr>
                                <th>번호</th>
                                <th>제목</th>
                                <th>글쓴이</th>
                                <th>날짜</th>
                                <th>조회 수</th>
                            </tr>
                            </thead>
                            <tbody>
                            {!board.posts || board.posts.length === 0 ? (
                                <tr>
                                    <td colSpan={5} style={{textAlign: "center"}}>
                                        게시글이 없습니다.
                                    </td>
                                </tr>
                            ) : (
                                board.posts.map((post) => (

                                    <tr key={post.id}>
                                        <td>{post.id}</td>
                                        <td>{post.title}</td>
                                        <td>{post.user.name}</td>
                                        <td>{post.updatedAt}</td>
                                        <td>{post.views}</td>
                                    </tr>
                                ))
                            )}
                            </tbody>
                        </table>
                    </div>
                </div>
            </main>
            <Footer/>
        </div>
    );
}

export default PostList;