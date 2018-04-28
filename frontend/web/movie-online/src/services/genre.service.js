import axios from 'axios';
import { Genre } from '../shared/api';

export const fetch = () => {
  return axios.get(Genre.GET_ALL).then(map);
}

export const create = genre => {
  return axios.post(Genre.CREATE, genre);
}

export const edit = (id, genre) => {
  return axios.put(Genre.EDIT + '/' + id, genre);
}

export const remove = id => {
  return axios.delete(Genre.DELETE + '/' + id);
}

const map = response => response.data;