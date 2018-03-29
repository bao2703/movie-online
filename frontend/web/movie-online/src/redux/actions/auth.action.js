import { createActions } from 'redux-actions';

export const authActions = createActions({
  LOGIN: (email, password) => ({ email, password })
});
