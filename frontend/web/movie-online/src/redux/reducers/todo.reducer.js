import { handleActions } from 'redux-actions';
import { Add } from '../actions/todo.action';

const initialState = [];

const todoReducer = handleActions({
  [Add](state, { payload }) {
    return [...state, payload]
  }
}, initialState);

export default todoReducer