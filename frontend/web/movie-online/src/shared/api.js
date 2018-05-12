const Api = {
  AUTH: 'auth',
  MOVIE: 'movies',
  GENRE: 'genres',
  EPISODE: 'episodes',
  STATISTIC: 'statistics'
}

export const Auth = {
  LOGIN: Api.AUTH + '/login'
}

export const Movie = {
  GET_ALL: Api.MOVIE,
  GET: Api.MOVIE,
  CREATE: Api.MOVIE,
  EDIT: Api.MOVIE,
  DELETE: Api.MOVIE,
  UPLOAD: Api.MOVIE + "/upload",
  GET_EPISODE: Api.MOVIE + "/episodes",
  CREATE_EPISODE: Api.MOVIE + "/episodes"
}

export const Genre = {
  GET_ALL: Api.GENRE,
  CREATE: Api.GENRE,
  EDIT: Api.GENRE,
  DELETE: Api.GENRE
}

export const Episode = {
  EDIT: Api.EPISODE,
  DELETE: Api.EPISODE
}

export const Statistic = {
  USER: Api.STATISTIC
}