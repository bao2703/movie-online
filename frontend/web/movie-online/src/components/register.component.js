import React, { Component } from 'react';
import { Link } from 'react-router-dom';

import { connect } from 'react-redux';
import { AuthActions } from '../redux/actions';

class Register extends Component {

  constructor(props) {
    super(props);
    this.state = {
      email: '',
      password: '',
      confirmPassword: ''
    }
  }

  onRegister = () => {
    this.props.register(this.state.email, this.state.password);
  }

  render() {
    return (
      <div>
        <div>
          Email
          <input
            value={this.state.email}
            onChange={(e) => this.setState({ email: e.target.value })}
          />
        </div>
        <div>
          Password
          <input
            value={this.state.password}
            onChange={(e) => this.setState({ password: e.target.value })}
          />
        </div>
        <div>
          Confirm Password
          <input
            value={this.state.confirmPassword}
            onChange={(e) => this.setState({ confirmPassword: e.target.value })}
          />
        </div>
        <div>
          <button onClick={this.onRegister}>Register</button>
          <Link to='/login'>
            Login
          </Link>
        </div>
      </div>
    )
  }
}

const mapStateToProps = (state) => ({

})

const mapDispatchToProps = {
  register: AuthActions.register
}

export default connect(mapStateToProps, mapDispatchToProps)(Register)
