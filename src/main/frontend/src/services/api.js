import axios from "axios";

export const fetchMovies = async () => {
    const response = await axios.get('/api/movies');
    return response.data;
};

export const test = async () => {
    const response = await axios.get('/api/test');
    return response.data;
};