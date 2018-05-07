import axios from '../shared/axios';
import { Episode } from '../shared/api';

export const edit = (id, episode) => {
  const formData = new FormData();
  formData.append('name', episode.name);
  formData.append('file', episode.file);
  return axios.put(Episode.EDIT + '/' + id, formData, { 'content-type': 'multipart/form-data' });
}

export const remove = id => {
  return axios.delete(Episode.DELETE + '/' + id);
}
