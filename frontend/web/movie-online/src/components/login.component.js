import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import { authActions } from '../redux/actions/auth.action';

export class Login extends Component {
  static propTypes = {
  }

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
            type="password"
            value={this.state.password}
            onChange={(e) => this.setState({ password: e.target.value })}
          />
        </div>
        <div>
          <button onClick={this.onLogin}>
            Login
          </button>
          </div>
      </div>
    )
  }
}

const mapStateToProps = (state) => ({

})

const mapDispatchToProps = {
  login: authActions.login
}

export default connect(mapStateToProps, mapDispatchToProps)(Login)
