import axios from '../shared/axios';
import { Movie } from '../shared/api';

export const fetch = () => {
  return axios.get(Movie.GET_ALL).then(map);
}

export const create = movie => {
  return axios.post(Movie.CREATE, movie);
}

export const edit = (id, movie) => {
  return axios.put(Movie.EDIT + '/' + id, movie);
}

export const remove = id => {
  return axios.delete(Movie.DELETE + '/' + id);
}

export const upload = file => {
  const formData = new FormData();
  formData.append('file', file);
  return axios.post(Movie.UPLOAD, formData, { 'content-type': 'multipart/form-data' }).then(map);
}

const map = response => response.data;