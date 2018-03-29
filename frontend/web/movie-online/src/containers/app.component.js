import React, { Component } from 'react';
import { connect } from 'react-redux';
import Todo from './../components/todo.component';
import Login from './../components/login.component';
import { todoActions } from '../redux/actions/todo.action';

export class App extends Component {

  constructor(props) {
    super(props);

    this.state = {
      text: ''
    }
  }

  onChange = (e) => {
    this.setState({ text: e.target.value });
  }

  onClick = () => {
    if (this.state.text) {
      this.props.addTodo(this.state.text);
    }
  }

  render() {
    return (
      <div>
        <div>
          <input value={this.state.text} onChange={this.onChange} />
          <button onClick={this.onClick}>Add</button>
        </div>
        {this.props.todos.map(todo =>
          <Todo key={todo.id} todo={todo} />
        )}
        <Login />
      </div>
    );
  }
}

const mapStateToProps = (state) => ({
  todos: state.todoReducer
})

const mapDispatchToProps = {
  addTodo: todoActions.add
}

export default connect(mapStateToProps, mapDispatchToProps)(App)

