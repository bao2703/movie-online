import { createAction } from 'redux-actions';
import { push } from 'react-router-redux';
import { authService } from '../../services';

const prefix = '[Auth]';

export const LoginRequest = createAction(prefix + '[LoginRequest]');
export const LoginSuccess = createAction(prefix + '[LoginSuccess]');
export const LoginFailure = createAction(prefix + '[LoginFailure]');

const login = (email, password) => {
  return dispatch => {
    dispatch(LoginRequest());

    authService.login(email, password)
      .then(response => {
        dispatch(LoginSuccess(response.data));
        dispatch(push('/'));
      })
      .catch(error => {
        dispatch(LoginFailure(error));
      });
  };
}

export const authActions = {
  login
};