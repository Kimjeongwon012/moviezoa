import axios from "axios";

export const fetchMostPopularMovies = async (amount) => {
    const response = await axios.get('/api/movies/top-popular/' + amount);
    console.log(response.data);
    return response.data;
};

export const fetchMovieById = async (id) => {
    const response = await axios.get('/api/movies/' + id);
    return response.data;
};