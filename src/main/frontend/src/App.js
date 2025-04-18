import React from "react";
import "lite-youtube-embed/src/lite-yt-embed.css";
import axios from "axios";
import {BrowserRouter as Router, Routes, Route} from "react-router-dom";
import Home from "./pages/Home";
import MovieDetail from "./pages/Movie/MovieDetail";
import Login from "./pages/Login";
import NotFound from "./pages/NotFound";
import PostList from "./pages/Board/PostList";

function App() {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<Home/>}/>
                <Route path="/movie/:id" element={<MovieDetail/>}/>
                <Route path="/login" element={<Login/>}/>
                <Route path="/board/:id" element={<PostList/>}/>
                <Route path="/*" element={<NotFound/>}/>
            </Routes>
        </Router>
    );
}

export default App;

