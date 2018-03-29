import { combineReducers } from 'redux';
import todoReducer from './todo.reducer';
import authReducer from './auth.reducer';

const rootReducer = combineReducers({
  todoReducer,
  authReducer
})

export default rootReducer;