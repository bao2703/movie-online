import axios from 'axios';
import { Movie } from '../shared/api';

export const fetch = () => {
  return axios.get(Movie.GET_ALL).then(map);
}

export const create = movie => {
  return axios.post(Movie.CREATE, movie);
}

const map = response => response.data;