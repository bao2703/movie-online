import React, { Component } from 'react';
import { connect } from 'react-redux';
import Todo from './../components/Todo';
import { addTodo } from '../redux/actions/todo.action';

export class App extends Component {

  constructor(props) {
    super(props);

    this.state = {
      text: ''
    }
  }

  handleChange = (event) => {
    this.setState({ text: event.target.value });
  }

  handleClick = () => {
    this.props.addTodo(this.state.text);
  }

  render() {
    return (
      <div>
        <div>
          <input value={this.state.text} onChange={this.handleChange} />
          <button onClick={this.handleClick}>Add</button>
        </div>
        {this.props.todos.map(todo =>
          <Todo key={todo.id} text={todo.text} />
        )}
      </div>
    );
  }
}

const mapStateToProps = (state) => ({
  todos: state.todoReducer
})

const mapDispatchToProps = {
  addTodo
}

export default connect(mapStateToProps, mapDispatchToProps)(App)

