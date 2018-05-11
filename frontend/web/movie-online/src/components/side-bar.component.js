import React, { Component } from 'react'
import { Link } from 'react-router-dom';

import List, { ListItem, ListItemText } from 'material-ui/List';

export default class SideBar extends Component {
  render() {
    return (
      <div>
        <List component="nav">
          <Link to="/">
            <ListItem button>
              <ListItemText className="text-center" primary="Dashboard" />
            </ListItem>
          </Link>
          <Link to="/movie">
            <ListItem button>
              <ListItemText className="text-center" primary="Movies" />
            </ListItem>
          </Link>
          <Link to="/genre">
            <ListItem button>
              <ListItemText className="text-center" primary="Genres" />
            </ListItem>
          </Link>
        </List>
      </div>
    )
  }
}
