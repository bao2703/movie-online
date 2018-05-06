const Api = {
  AUTH: 'auth',
  MOVIE: 'movies',
  GENRE: 'genres'
}

export const Auth = {
  LOGIN: 'login'
}

export const Movie = {
  GET_ALL: Api.MOVIE,
  CREATE: Api.MOVIE,
  EDIT: Api.MOVIE,
  DELETE: Api.MOVIE,
  UPLOAD: Api.MOVIE + "/upload"
}

export const Genre = {
  GET_ALL: Api.GENRE,
  CREATE: Api.GENRE,
  EDIT: Api.GENRE,
  DELETE: Api.GENRE
}