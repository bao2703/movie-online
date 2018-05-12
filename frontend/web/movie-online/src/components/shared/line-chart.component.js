import React, { Component } from 'react';
import { Chart } from 'react-google-charts';

export class LineChart extends Component {

  static defaultProps = {
    colors: ['#3366cc'],
    position: 'none',
    width: '100%',
    height: '500px'
  }

  constructor(props) {
    super(props);

    this.state = {
      options: {
        title: this.props.title,
        colors: this.props.colors,
        hAxis: {
          title: this.props.hAxis,
          textStyle: {
            fontSize: 12
          }
        },
        vAxis: { title: this.props.vAxis },
        legend: { position: this.props.position },
        height: this.height,
        chartArea: { width: '90%' },
        animation: {
          startup: true,
          easing: 'inAndOut',
          duration: 1000,
        }
      }
    }
  }


  render() {
    const { options } = this.state;
    const { data, width, height } = this.props;

    return (
      <div>
        <Chart
          chartType="LineChart"
          data={data}
          options={options}
          width={width}
          height={height}
        />
      </div>
    )
  }
}