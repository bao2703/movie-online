import axios from 'axios';
import { Genre } from '../shared/api';

export const fetch = () => {
  return axios.get(Genre.GET_ALL).then(map);
}
  
export const create = genre => {
  return axios.post(Genre.CREATE, genre);
}
  
const map = response => response.data;