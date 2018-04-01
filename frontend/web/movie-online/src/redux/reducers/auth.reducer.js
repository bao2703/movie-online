import { handleActions } from 'redux-actions';

const initialState = [];

export const AuthReducer = handleActions({
  LOGIN: (state, { payload }) => {
    return { ...state, payload };
  },
  REGISTER: (state, { payload }) => {
    return { ...state, payload };
  }
}, initialState);
