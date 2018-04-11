import { createActions } from 'redux-actions';
import { push } from 'react-router-redux';
import { authService } from '../../services';

const {
  loginRequest,
  loginSuccess,
  loginFailure,
  logout,
  checkLogin
} = createActions(
  'LOGIN_REQUEST',
  'LOGIN_SUCCESS',
  'LOGIN_FAILURE',
  'LOGOUT',
  "CHECK_LOGIN"
);

const startLogin = (email, password) => {
  return dispatch => {
    dispatch(loginRequest());
    authService.login(email, password)
      .then(() => {
        dispatch(loginSuccess());
        dispatch(push('/'));
      })
      .catch(error => dispatch(loginFailure(new Error(error))));
  };
}

const startlogout = () => {
  return dispatch => {
    authService.logout();
    dispatch(logout());
    dispatch(push('/login'));
  };
}

const startCheckLogin = () => {
  return dispatch => {
    dispatch(checkLogin());
    if (authService.isAuthenticated()) {
      dispatch(loginSuccess());
    } else {
      dispatch(loginFailure());
      dispatch(push('/login'));
    }
  }
}

export const authActions = {
  startLogin,
  startlogout,
  startCheckLogin
};