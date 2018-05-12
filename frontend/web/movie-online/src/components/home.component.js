import React, { Component } from 'react';
import { LineChart } from './shared/line-chart.component';

import * as statisticService from '../services/statistic.service';

export class Home extends Component {

  constructor(pros) {
    super(pros);

    this.state = {
      data: []
    }
  }

  componentDidMount() {
    statisticService.user().then(response => {
      this.setState({
        data: [
          ['Title', 'User'],
          ...response.reverse().map(item => [item.key, item.value])
        ]
      });
    });
  }

  render() {
    const { data } = this.state;

    return (
      <div>
        {data.length !== 0 && <LineChart data={data} title="User Per Month" />}
      </div>
    )
  }
}

export default Home
