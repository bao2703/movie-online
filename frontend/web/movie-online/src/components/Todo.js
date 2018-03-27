import React, { Component } from 'react';
import PropTypes from 'prop-types';

export default class Todo extends Component {
  static propTypes = {
    text: PropTypes.any.isRequired
  }

  render() {
    return (
      <div>
        {this.props.text}
      </div>
    )
  }
}
