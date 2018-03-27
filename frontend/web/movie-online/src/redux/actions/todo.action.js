export const ActionTypes = {
	ADD: 'ADD',
	SHOW: 'SHOW',
};

let nextTodoId = 1
export const addTodo = (text) => {
  return {
    type: ActionTypes.ADD,
    payload: {id: nextTodoId++, text}
  }
}