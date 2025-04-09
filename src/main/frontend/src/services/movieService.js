import axios from "axios";

export const fetchMostPopularMovies = async (amount) => {
    const response = await axios.get('/api/movie/top-popular/' + amount);
    return response.data;
};

export const fetchMovieById = async (id) => {
    const response = await axios.get('/api/movie/' + id);
    return response.data;
};

export const fetchReviews = async (id, sort, page, size) => {
    const response = await axios.get(`/api/movie/${id}/review`, {
        params: {sort, page, size}
    });
    return response.data;
};


export const postReview = async (id, content, rating) => {
    const response = await axios.post(`/api/movie/${id}/review`, {
        content,
        rating
    });
};