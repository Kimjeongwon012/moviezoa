import React from "react";
import styles from "./Header.module.css";
import {Link} from 'react-router-dom';

function Header() {
    return (
        <header className={styles.header}>
            <div className={styles.navbar}>
                <div className={styles.logo}>
                    <img src="logo.png" alt="IMDB"/>
                </div>
                <nav className={styles.nav}>
                    <button className={styles.menuBtn}>☰ Menu</button>
                    <select className={styles.dropdown}>
                        <option>All</option>
                        <option>Movies</option>
                        <option>TV Shows</option>
                    </select>
                    <input type="text" placeholder="Search IMDb" className={styles.searchBox}/>
                    <button className={styles.searchBtn}>🔍</button>
                    <div className={styles.userActions}>
                        <Link to="#">IMDbPro</Link>
                        <Link to="#">Watchlist</Link>
                        <Link to="/login">Sign In</Link>
                        <select className={styles.langSelect}>
                            <option>EN</option>
                            <option>KR</option>
                        </select>
                    </div>
                </nav>
            </div>
        </header>
    );
}

export default Header;