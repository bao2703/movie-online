import React, { Component } from 'react';

import { BrowserRouter as Router, Route, Switch, Link } from 'react-router-dom';

import Header from './../components/header.component';
import Home from './../components/home.component';
import Movie from './../components/movie.component';
import Login from './../components/login.component';

export class App extends Component {

  render() {
    return (
      <div>
        <Router>
          <div>
            <Header />
            <Link to="/login">Login</Link>
            <Link to="/movie">Movie</Link>
            <Switch>
              <Route exact path="/" component={Home} />
              <Route path="/movie" component={Movie} />
              <Route path="/login" component={Login} />
            </Switch>
          </div>
        </Router>
      </div>
    );
  }
}

export default App
