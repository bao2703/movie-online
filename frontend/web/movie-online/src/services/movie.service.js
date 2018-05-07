import axios from '../shared/axios';
import { Movie } from '../shared/api';

export const fetchAll = () => {
  return axios.get(Movie.GET_ALL).then(map);
}

export const fetch = id => {
  return axios.get(Movie.GET + '/' + id).then(map);
}

export const create = movie => {
  return axios.post(Movie.CREATE, movie);
}

export const edit = (id, movie) => {
  const formData = new FormData();
  formData.append('name', movie.name);
  formData.append('description', movie.description);
  formData.append('file', movie.file);
  return axios.put(Movie.EDIT + '/' + id, formData, { 'content-type': 'multipart/form-data' });
}

export const remove = id => {
  return axios.delete(Movie.DELETE + '/' + id);
}

export const fetchEpisodes = id => {
  return axios.get(Movie.GET_EPISODE + '/' + id).then(map);
}

export const addEpisode = (id, episode) => {
  const formData = new FormData();
  formData.append('name', episode.name);
  formData.append('file', episode.file);
  return axios.post(Movie.CREATE_EPISODE + '/' + id, formData, { 'content-type': 'multipart/form-data' });
}

const map = response => response.data;