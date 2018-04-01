import React, { Component } from 'react';
import { BrowserRouter, Switch, Route } from 'react-router-dom';

import { connect } from 'react-redux';

import Header from './../components/header.component';
import Home from './../components/home.component';
import Login from './../components/login.component';
import Register from './../components/register.component';

export class App extends Component {

  constructor(props) {
    super(props);

    this.state = {
    }
  }

  render() {
    return (
      <div>
        <BrowserRouter>
          <div>
            <Header />
            <Switch>
              <Route exact path='/' component={Home} />
              <Route path='/login' component={Login} />
              <Route path='/register' component={Register} />
            </Switch>
          </div>
        </BrowserRouter>
      </div>
    );
  }
}

const mapStateToProps = (state) => ({

})

const mapDispatchToProps = {

}

export default connect(mapStateToProps, mapDispatchToProps)(App)

