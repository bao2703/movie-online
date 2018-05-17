import axios from '../shared/axios';
import { Auth } from '../shared/api';

const Storage = {
  TOKEN: 'token'
}

export const login = (email, password) => {
  return axios.post(Auth.LOGIN, { email, password })
    .then(response => response.data)
    .then(response => {
      if (response.role === 'Administrator') {
        localStorage.setItem(Storage.TOKEN, response.jwt);
      } else {
        throw new DOMException();
      }
    });
}

export const logout = () => {
  localStorage.removeItem(Storage.TOKEN);
}

export const isLoggedIn = () => {
  const token = localStorage.getItem(Storage.TOKEN);

  return token != null;
}
