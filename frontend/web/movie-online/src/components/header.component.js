import React, { Component } from 'react';
import { Link } from 'react-router-dom';

import { connect } from 'react-redux';

export class Header extends Component {

  render() {
    return (
      <div>
        <ul>
          <li>
            <Link to='/'>Home</Link>
            <Link to='/register'>Register</Link>
            <Link to='/login'>Login</Link>
          </li>
        </ul>
      </div>
    )
  }
}

const mapStateToProps = (state) => ({

})

const mapDispatchToProps = {

}

export default connect(mapStateToProps, mapDispatchToProps)(Header)
