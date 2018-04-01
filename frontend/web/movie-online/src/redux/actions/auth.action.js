import { createActions } from 'redux-actions';

export const AuthActions = createActions({
  LOGIN: (email, password) => ({ email, password }),
  REGISTER: (email, password) => ({ email, password })
});
