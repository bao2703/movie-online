import React, { Component } from 'react';
import * as movieService from '../services/movie.service';

import Table, { TableBody, TableCell, TableHead, TableRow } from 'material-ui/Table';

export class Movie extends Component {

  constructor(props) {
    super(props);

    this.state = {
      movies: [],
      name: ''
    }
  }

  componentDidMount() {
    movieService.fetch().then(movies => {
      this.setState({ movies })
    });
  }

  onTextChange = name => e => {
    this.setState({ [name]: e.target.value });
  }

  add = () => {
    const movie = {name: this.state.name};
    movieService.create(movie).then(() => {
      this.componentDidMount();
    })
  }

  render() {
    const { movies } = this.state;

    return (
      <div>
        <input onChange={this.onTextChange('name')} />
        <button onClick={() => this.add()}>Add</button>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell>Id</TableCell>
              <TableCell>Name</TableCell>
              <TableCell>Views</TableCell>
              <TableCell>Description</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {movies.map(movie =>
              <TableRow key={movie.id}>
                <TableCell>{movie.id}</TableCell>
                <TableCell>{movie.name}</TableCell>
                <TableCell>{movie.views}</TableCell>
                <TableCell>{movie.description}</TableCell>
              </TableRow>
            )}
          </TableBody>
        </Table>
      </div>
    )
  }
}

export default Movie
