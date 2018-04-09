import axios from 'axios';
import { Auth } from '../shared/api';

const login = (email, password) => {
  return axios.post(Auth.LOGIN, { email, password });
}

export const authService = {
  login
};