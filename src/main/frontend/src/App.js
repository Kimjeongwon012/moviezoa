import React from "react";
import "lite-youtube-embed/src/lite-yt-embed.css";
import axios from "axios";
import {BrowserRouter as Router, Routes, Route} from "react-router-dom";
import Home from "./pages/Home";
import MovieDetail from "./pages/MovieDetail";
import NotFound from "./pages/NotFound";

function App() {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<Home/>}/>
                <Route path="/movies/:id" element={<MovieDetail/>}/>
            </Routes>
        </Router>
    );
}

export default App;

