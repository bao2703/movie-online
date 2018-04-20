import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom';

import * as authService from '../services/auth.service';

import AppBar from 'material-ui/AppBar';
import Toolbar from 'material-ui/Toolbar';
import Typography from 'material-ui/Typography';
import Button from 'material-ui/Button';
import IconButton from 'material-ui/IconButton';
import MenuIcon from 'material-ui-icons/Menu';

export class Header extends Component {

  logout = () => {
    authService.logout();
    this.props.history.push('/login');
  }

  render() {
    const { isLoggedIn } = false;

    return (
      <div>
        <AppBar position="static" color="primary">
          <Toolbar>
            <IconButton color="inherit">
              <MenuIcon />
            </IconButton>
            <div style={styles.appTitle}>
              <Button color="inherit" component={Link} to="/">
                <Typography variant="title" color="inherit">
                  Admin
                </Typography>
              </Button>
            </div>
            {isLoggedIn &&
              <Button className="ml-auto" color="inherit" onClick={() => this.logout()}>
                Log out
              </Button>
            }
          </Toolbar>
        </AppBar>
      </div>
    )
  }
}

const styles = {
  appTitle: {
    marginLeft: 20
  }
}

export default withRouter(Header)
