import { createAction } from 'redux-actions';

const ActionTypes = {
	ADD: 'ADD',
	SHOW: 'SHOW',
};

export const Add = createAction(ActionTypes.ADD);

let nextTodoId = 1
export const addTodo = (text) => {
  return {
    type: ActionTypes.ADD,
    payload: {id: nextTodoId++, text}
  }
}