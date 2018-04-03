import React, { Component } from 'react';
import { Link } from 'react-router-dom';

import { connect } from 'react-redux';

import AppBar from 'material-ui/AppBar';
import Toolbar from 'material-ui/Toolbar';
import Typography from 'material-ui/Typography';
import Button from 'material-ui/Button';
import IconButton from 'material-ui/IconButton';
import MenuIcon from 'material-ui-icons/Menu';

export class Header extends Component {

  render() {
    return (
      <div>
        <AppBar position="static" color="primary">
          <Toolbar>
            <IconButton color="inherit">
              <MenuIcon />
            </IconButton>
            <Button style={styles.appTitle} color="inherit" component={Link} to="/">
              <Typography variant="title" color="inherit">
                Admin
              </Typography>
            </Button>
            <Button style={styles.logoutButton} color="inherit">Log out</Button>
          </Toolbar>
        </AppBar>
      </div>
    )
  }
}

const styles = {
  logoutButton: {
    marginLeft: 'auto'
  },
  appTitle: {
    marginLeft: 20
  }
}

const mapStateToProps = (state) => ({

})

const mapDispatchToProps = {

}

export default connect(mapStateToProps, mapDispatchToProps)(Header)
