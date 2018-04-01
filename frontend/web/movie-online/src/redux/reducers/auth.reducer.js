import { handleActions } from 'redux-actions';

const INITIAL_STATE = [];

export const AuthReducer = handleActions({
  LOGIN: (state, { payload }) => {
    return { ...state, payload };
  },
  REGISTER: (state, { payload }) => {
    return { ...state, payload };
  }
}, INITIAL_STATE);
