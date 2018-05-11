import axios from '../shared/axios';
import { Statistic } from '../shared/api';

export const user = () => {
  return axios.get(Statistic.USER).then(map);
}

const map = response => response.data;