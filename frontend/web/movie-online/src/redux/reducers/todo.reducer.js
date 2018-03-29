import { handleActions } from 'redux-actions';

const initialState = [];

const todoReducer = handleActions({
  ADD: (state, action) => {
    return [...state, action.payload];
  },
  DELETE: (state, action) => {
    return state.filter(todo => todo.id !== action.payload.id);
  }
}, initialState);

export default todoReducer