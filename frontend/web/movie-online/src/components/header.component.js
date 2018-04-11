import React, { Component } from 'react';
import { Link } from 'react-router-dom';

import { connect } from 'react-redux';
import { authActions } from '../redux/actions';

import AppBar from 'material-ui/AppBar';
import Toolbar from 'material-ui/Toolbar';
import Typography from 'material-ui/Typography';
import Button from 'material-ui/Button';
import IconButton from 'material-ui/IconButton';
import MenuIcon from 'material-ui-icons/Menu';

export class Header extends Component {

  render() {
    const { isAuthenticated } = this.props;

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
            {isAuthenticated &&
              <Button className="ml-auto" color="inherit" onClick={() => this.props.startlogout()}>
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

const mapStateToProps = (state) => ({
  isAuthenticated: state.auth.isAuthenticated
})

const mapDispatchToProps = {
  startlogout: authActions.startlogout
}

export default connect(mapStateToProps, mapDispatchToProps)(Header)
