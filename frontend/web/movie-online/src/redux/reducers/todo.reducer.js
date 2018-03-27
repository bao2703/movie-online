import { ActionTypes } from '../actions/todo.action';

const initialState = [];

const todoReducer = (state = initialState, action) => {
  switch (action.type) {
    case ActionTypes.ADD:
      return [
        ...state,
        {
          id: action.payload.id,
          text: action.payload.text
        }
      ]
    default:
      return state
  }
}

export default todoReducer