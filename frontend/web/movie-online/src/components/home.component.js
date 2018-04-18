import React, { Component } from 'react';
import { movieService } from '../services';

import Table, { TableBody, TableCell, TableHead, TableRow } from 'material-ui/Table';

export class Home extends Component {

  constructor(props) {
    super(props);

    this.state = {
      movies: []
    }
  }

  componentDidMount() {
    movieService.fetcMovies().then(movies => {
      this.setState({ movies })
    });
  }

  render() {
    const { movies } = this.state;

    return (
      <div>
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

export default Home
