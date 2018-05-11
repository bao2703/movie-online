import React, { Component } from 'react';

import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';

import Header from './../components/header.component';
import SideBar from './../components/side-bar.component';
import Home from './../components/home.component';
import Movie from './../components/movie.component';
import Login from './../components/login.component';
import Genre from './../components/genre.component';
import MovieDetail from './../components/movie-detail.component';

export class App extends Component {

  render() {
    return (
      <div className="">
        <Router>
          <div>
            <Header />
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
          </div>
        </Router>
      </div>
    );
  }
}

export default App
