import { createActions } from 'redux-actions';

let nextTodoId = 1
export const todoActions = createActions({
  ADD: (text) => ({ id: nextTodoId++, text }),
  DELETE: (id) => ({ id })
});
