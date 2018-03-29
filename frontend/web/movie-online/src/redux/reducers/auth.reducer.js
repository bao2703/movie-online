import { handleActions } from 'redux-actions';

const initialState = [];

const authReducer = handleActions({
  LOGIN: (state, action) => {
    return [...state, action.payload];
  }
}, initialState);

export default authReducer