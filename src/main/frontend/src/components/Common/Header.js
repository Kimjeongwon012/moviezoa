import React from "react";
import "./Header.css";
function Header() {
    return (
        <header className="header">
            <div className="navbar">
                <div className="logo">
                    <img src="logo.png" alt="IMDB"/>
                </div>
                <nav>
                    <button className="menu-btn">☰ Menu</button>
                    <select className="dropdown">
                        <option>All</option>
                        <option>Movies</option>
                        <option>TV Shows</option>
                    </select>
                    <input type="text" placeholder="Search IMDb" className="search-box" />
                    <button className="search-btn">🔍</button>
                    <div className="user-actions">
                        <a href="#">IMDbPro</a>
                        <a href="#">Watchlist</a>
                        <a href="#">Sign In</a>
                        <select className="lang-select">
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