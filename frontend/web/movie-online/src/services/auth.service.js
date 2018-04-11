import axios from 'axios';
import { Auth } from '../shared/api';

const Storage = {
  TOKEN: 'token'
}

const login = (email, password) => {
  return axios.post(Auth.LOGIN, { email, password })
    .then(response => {
      localStorage.setItem(Storage.TOKEN, response.data);
    });
}

const logout = () => {
  localStorage.removeItem(Storage.TOKEN);
}

const isAuthenticated = () => {
  const token = localStorage.getItem(Storage.TOKEN);

  return token != null;
}

export const authService = {
  login,
  logout,
  isAuthenticated
};