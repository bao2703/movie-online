const BASE_URL = 'http://localhost:5000/api';

const Api = {
  AUTH: BASE_URL + '/auth'
}

export const Auth = {
  LOGIN: Api.AUTH + '/login'
}