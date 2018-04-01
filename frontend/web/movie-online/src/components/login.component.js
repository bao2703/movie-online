import React, { Component } from 'react';
import { Link } from 'react-router-dom';

import { connect } from 'react-redux';
import { AuthActions } from '../redux/actions';

class Login extends Component {

  constructor(props) {
    super(props);
    this.state = {
      email: '',
      password: ''
    }
  }

  onLogin = () => {
    this.props.login(this.state.email, this.state.password);
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
            type='password'
            value={this.state.password}
            onChange={(e) => this.setState({ password: e.target.value })}
          />
        </div>
        <div>
          <button onClick={this.onLogin}>Login</button>
          <Link to='/register'>
            Register
          </Link>
        </div>
      </div>
    )
  }
}

const mapStateToProps = (state) => ({

})

const mapDispatchToProps = {
  login: AuthActions.login
}

export default connect(mapStateToProps, mapDispatchToProps)(Login)
