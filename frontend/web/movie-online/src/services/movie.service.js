import axios from 'axios';
import { Movie } from '../shared/api';

const fetcMovies = () => {
  return axios.get(Movie.GET_ALL).then(map);
}

export const movieService = {
  fetcMovies
};

const map = response => response.data;