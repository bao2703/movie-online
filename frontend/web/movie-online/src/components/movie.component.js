import React, { Component } from 'react';
import * as movieService from '../services/movie.service';

import Button from 'material-ui/Button';
import TextField from 'material-ui/TextField';
import Table, { TableBody, TableCell, TableHead, TableRow } from 'material-ui/Table';
import Dialog, { DialogActions, DialogContent, DialogContentText, DialogTitle } from 'material-ui/Dialog';

export class Movie extends Component {

  constructor(props) {
    super(props);

    this.state = {
      movies: [],
      name: '',
      description: '',
      open: false,
      selectedMovie: { name: '', description: '' }
    }
  }

  openDialog = selectedMovie => {
    this.setState({
      open: true,
      selectedMovie
    });
  };

  closeDialog = () => {
    this.setState({ open: false });
    this.fetchMovies();
  };

  componentDidMount() {
    this.fetchMovies();
  }

  onTextChange = name => e => {
    this.setState({ [name]: e.target.value });
  }

  fetchMovies = () => {
    movieService.fetch().then(movies => {
      this.setState({ movies })
    });
  }

  add = () => {
    const movie = {name: this.state.name};
    movieService.create(movie).then(() => {
      this.componentDidMount();
    })
  }

  render() {
    const { movies, selectedMovie } = this.state;

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
              <TableRow key={movie.id} onClick={() => this.openDialog(movie)}>
                <TableCell>{movie.id}</TableCell>
                <TableCell>{movie.name}</TableCell>
                <TableCell>{movie.views}</TableCell>
                <TableCell>{movie.description}</TableCell>
              </TableRow>
            )}
          </TableBody>
        </Table>

        <EditMovieDialog
          open={this.state.open}
          onClose={() => this.closeDialog()}
          movie={selectedMovie}
        />
      </div>
    )
  }
}

export default Movie

class EditMovieDialog extends Component {
  constructor(props) {
    super(props);

    this.state = {
      id: '',
      name: '',
      description: ''
    }
  }

  componentWillReceiveProps(nextProps) {
    const { movie } = nextProps;
    this.setState({
      id: movie.id,
      name: movie.name,
      description: movie.description
    });
  }

  onEdit = () => {
    const { id, name, description } = this.state;
    movieService.edit(id, { name, description }).then(() => {
      this.props.onClose();
    })
  }

  onRemove = () => {
    const { id } = this.state;
    movieService.remove(id).then(() => {
      this.props.onClose();
    });
  }

  onTextChange = name => e => {
    this.setState({ [name]: e.target.value });
  }

  render() {
    const { ...other } = this.props;
    const { name, description } = this.state;

    return (
      <Dialog {...other}>
        <DialogTitle>Edit</DialogTitle>
        <DialogContent>
          <TextField
            autoFocus
            margin="normal"
            label="Name"
            value={name}
            onChange={this.onTextChange('name')}
            fullWidth
          />
          <TextField
            margin="normal"
            label="Description"
            value={description}
            onChange={this.onTextChange('description')}
            fullWidth
          />
        </DialogContent>
        <DialogActions>
          <Button onClick={() => this.props.onClose()} color="primary">
            Cancel
          </Button>
          <Button onClick={() => this.onRemove()} color="primary">
            Remove
          </Button>
          <Button onClick={() => this.onEdit()} color="primary">
            Save
          </Button>
        </DialogActions>
      </Dialog>
    )
  }
}
