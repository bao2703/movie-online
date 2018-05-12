import React, { Component } from 'react';

import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';

import Header from './../components/core/header.component';
import SideBar from './../components/core/side-bar.component';
import Home from './../components/home.component';
import Movie from './../components/movie.component';
import Login from './../components/login.component';
import Genre from './../components/genre.component';
import MovieDetail from './../components/movie-detail.component';

import * as authService from '../services/auth.service';

export class App extends Component {

  constructor(props) {
    super(props);

    this.state = {
      isLoggedIn: authService.isLoggedIn()
    };
  }

  loginSuccess = () => {
    this.setState({ isLoggedIn: true });
  }

  onLogout = () => {
    this.setState({ isLoggedIn: false });
  }

  render() {
    return (
      <div>
        <Router>
          <div>
            <Header onLogout={this.onLogout} />
            {this.state.isLoggedIn ? (
              <div className="container-fluid">
                <div className="row border-bottom">
                  <div className="col-md-2 border-right">
                    <SideBar />
                  </div>
                  <div className="col-md-10 mt-3 pb-5">
                    <Switch>
                      <Route exact path="/" component={Home} />
                      <Route path="/movie/:id" component={MovieDetail} />
                      <Route path="/movie" component={Movie} />
                      <Route path="/genre" component={Genre} />
                    </Switch>
                  </div>
                </div>
              </div>
            ) : (
                <Login loginSuccess={this.loginSuccess} />
              )}
          </div>
        </Router>
      </div>
    );
  }
}

export default App
