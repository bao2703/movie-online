import { handleActions } from 'redux-actions'

const initialState = {
  fetching: false,
  isAuthenticated: false
};

export const authReducer = handleActions({
  LOGIN_REQUEST: state => ({
    ...state,
    fetching: true
  }),
  LOGIN_SUCCESS: state => ({
    ...state,
    fetching: false,
    isAuthenticated: true
  }),
  LOGIN_FAILURE: state => ({
    ...state,
    fetching: false,
    isAuthenticated: false
  }),
  LOGOUT: state => ({
    ...state,
    isAuthenticated: false
  })
}, initialState);
