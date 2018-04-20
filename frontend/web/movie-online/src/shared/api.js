const BASE_URL = 'http://localhost:5000/api';

const Api = {
  AUTH: BASE_URL + '/auth',
  MOVIE: BASE_URL + '/movies'
}

export const Auth = {
  LOGIN: Api.AUTH + '/login'
}

export const Movie = {
  GET_ALL: Api.MOVIE,
  CREATE: Api.MOVIE
}