import { handleActions } from 'redux-actions'
import { LoginRequest, LoginSuccess, LoginFailure } from '../actions/auth.action';

const initialState = {
  fetching: false
};

export const authReducer = handleActions({
  [LoginRequest]: (state, { payload }) => {
    return {
      ...state,
      fetching: true
    }
  },
  [LoginSuccess]: (state, { payload }) => {
    return {
      ...state,
      fetching: false
    }
  },
  [LoginFailure]: (state, { payload }) => {
    return {
      ...state,
      fetching: false
    }
  }
}, initialState);
