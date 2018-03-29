import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import { todoActions } from '../redux/actions/todo.action';

export class Todo extends Component {
  static propTypes = {
    todo: PropTypes.any.isRequired
  }

  constructor(props) {
    super(props);
  }


  render() {
    const todo = this.props.todo;
    return (
      <div>
        {todo.text}
        <button onClick={() => this.props.deleteTodo(todo.id)}>Delete</button>
      </div>
    )
  }
}

const mapStateToProps = (state) => ({

})

const mapDispatchToProps = {
  deleteTodo: todoActions.delete
}

export default connect(mapStateToProps, mapDispatchToProps)(Todo)
