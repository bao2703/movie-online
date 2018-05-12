import React, { Component } from 'react';

import * as authService from '../services/auth.service';

import AppBar from 'material-ui/AppBar';
import TextField from 'material-ui/TextField';
import Grid from 'material-ui/Grid';
import Button from 'material-ui/Button';
import Typography from 'material-ui/Typography';
import Toolbar from 'material-ui/Toolbar';

class Login extends Component {

  constructor(props) {
    super(props);
    this.state = {
      email: 'admin1@gmail.com',
      password: '1',
      fetching: false
    }
  }

  onTextChange = name => e => {
    this.setState({ [name]: e.target.value });
  }

  login = (email, password) => {
    this.setState({ fetching: true });
    authService.login(email, password).then(() => {
      this.setState({ fetching: false });
      this.props.loginSuccess();
    });
  }

  render() {
    const { email, password } = this.state;

    return (
      <div className="mt-5">
        <Grid container justify="center">
          <Grid item xs={12} sm={8} md={6}>
            <div className="card shadow">
              <AppBar position="sticky" color="primary">
                <Toolbar color="inherit">
                  <Typography variant="title" color="inherit" className="m-auto">Login</Typography>
                </Toolbar>
              </AppBar>
              <div className="card-body mb-3 pt-0">
                <TextField label="Email" type="email" fullWidth autoFocus required
                  margin="normal"
                  value={email}
                  onChange={this.onTextChange('email')}
                />
                <TextField label="Password" type="password" fullWidth required
                  margin="normal"
                  value={password}
                  onChange={this.onTextChange('password')}
                />
              </div>
              <div className="card-footer">
                <Button className="float-right"
                  color="primary"
                  variant="raised"
                  onClick={() => this.login(email, password)}
                  disabled={this.state.fetching}
                >
                  Log in
                </Button>
              </div>
            </div>
          </Grid>
        </Grid>
      </div>
    )
  }
}

export default Login
