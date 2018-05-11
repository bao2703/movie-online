import React, { Component } from 'react';
import { Link } from 'react-router-dom';

import * as movieService from '../services/movie.service';

import Button from 'material-ui/Button';
import TextField from 'material-ui/TextField';
import Table, { TableBody, TableCell, TableHead, TableRow } from 'material-ui/Table';
import Dialog, { DialogActions, DialogContent, DialogTitle } from 'material-ui/Dialog';

export class Movie extends Component {

  constructor(props) {
    super(props);

    this.state = {
      movies: [],
      name: '',
      description: '',
      openAdd: false,
      openEdit: false,
      openSearch: false,
      selectedMovie: { name: '', description: '' }
    }
  }

  openAddDialog = () => {
    this.setState({ openAdd: true });
  }

  closeAddDialog = () => {
    this.setState({ openAdd: false });
    this.fetchMovies();
  }

  openEditDialog = selectedMovie => {
    this.setState({
      openEdit: true,
      selectedMovie
    });
  }

  closeEditDialog = () => {
    this.setState({ openEdit: false });
    this.fetchMovies();
  }

  openSearchDialog = () => {
    this.setState({ openSearch: true });
  }

  closeSearchDialog = () => {
    this.setState({ openSearch: false });
  }

  componentDidMount() {
    this.fetchMovies();
  }

  onTextChange = name => e => {
    this.setState({ [name]: e.target.value });
  }

  fetchMovies = () => {
    movieService.fetchAll().then(movies => {
      this.setState({ movies })
    });
  }

  add = () => {
    const movie = { name: this.state.name };
    movieService.create(movie).then(() => {
      this.fetchMovies();
    })
  }

  render() {
    const { movies, selectedMovie } = this.state;

    return (
      <div>
        <Button color="primary" onClick={() => this.openAddDialog()}>
          Add
        </Button>
        
        <Button color="primary" onClick={() => this.openSearchDialog()}>
          Search
        </Button>

        <Table>
          <TableHead>
            <TableRow>
              <TableCell>Name</TableCell>
              <TableCell>Views</TableCell>
              <TableCell>Description</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {movies.map(movie =>
              <TableRow key={movie.id} onClick={() => this.openEditDialog(movie)} className="table-row">
                <TableCell>
                  <Link to={'/movie/' + movie.id}>{movie.name}</Link>
                </TableCell>
                <TableCell>{movie.views}</TableCell>
                <TableCell>{movie.description}</TableCell>
              </TableRow>
            )}
          </TableBody>
        </Table>

        <AddMovieDialog
          fullWidth
          open={this.state.openAdd}
          onClose={this.closeAddDialog}
        />

        <EditMovieDialog
          fullWidth
          open={this.state.openEdit}
          onClose={this.closeEditDialog}
          movie={selectedMovie}
        />

        <SearchDialog
          fullWidth
          open={this.state.openSearch}
          onClose={this.closeSearchDialog}
        />
      </div>
    )
  }
}

export default Movie

class AddMovieDialog extends Component {

  constructor(props) {
    super(props);

    this.state = {
      name: '',
      description: '',
      fetching: false
    }
  }

  componentWillReceiveProps(nextProps) {
    this.setState({
      name: '',
      description: '',
      fetching: false,
    })
  }

  onAdd = () => {
    this.setState({ fetching: true });
    const { name, description } = this.state;
    movieService.create({ name, description }).then(() => {
      this.props.onClose();
      this.setState({ fetching: false });
    })
  }

  onTextChange = name => e => {
    this.setState({ [name]: e.target.value });
  }

  render() {
    const { ...other } = this.props;
    const { name, description, fetching } = this.state;

    return (
      <Dialog {...other}>
        <DialogTitle>Add</DialogTitle>
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
          <Button onClick={() => this.onAdd()} color="primary" disabled={fetching}>
            Save
          </Button>
        </DialogActions>
      </Dialog>
    )
  }
}

class EditMovieDialog extends Component {
  constructor(props) {
    super(props);

    this.state = {
      id: '',
      name: '',
      posterUrl: '',
      description: '',
      fetching: false
    }
  }

  componentWillReceiveProps(nextProps) {
    const { movie } = nextProps;
    this.setState({
      id: movie.id,
      name: movie.name,
      posterUrl: 'http://localhost:5000' + movie.posterUrl,
      description: movie.description
    });
  }

  onEdit = () => {
    this.setState({ fetching: true });
    const { id, name, description, file } = this.state;
    movieService.edit(id, { name, description, file }).then(() => {
      this.props.onClose();
      this.setState({ fetching: false });
    });
  }

  onRemove = () => {
    this.setState({ fetching: true });
    const { id } = this.state;
    movieService.remove(id).then(() => {
      this.props.onClose();
      this.setState({ fetching: false });
    });
  }

  handleFileChange = e => {
    e.preventDefault();
    const file = e.target.files[0];
    this.setState({ file });
  }

  onTextChange = name => e => {
    this.setState({ [name]: e.target.value });
  }

  render() {
    const { ...other } = this.props;
    const { name, posterUrl, description, fetching } = this.state;

    return (
      <Dialog {...other}>
        <DialogTitle>Edit</DialogTitle>
        <DialogContent>
          <div align="center">
            <img src={posterUrl} className="img-fluid" alt="" style={{ height: 200, width: 200 }} />
          </div>
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
          <div align="center">
            <input type="file" className="mt-3" onChange={this.handleFileChange} />
          </div>
        </DialogContent>
        <DialogActions>
          <Button onClick={() => this.props.onClose()} color="primary">
            Cancel
          </Button>
          <Button onClick={() => this.onRemove()} color="primary" disabled={fetching}>
            Remove
          </Button>
          <Button onClick={() => this.onEdit()} color="primary" disabled={fetching}>
            Save
          </Button>
        </DialogActions>
      </Dialog>
    )
  }
}

class SearchDialog extends Component {

  constructor(props) {
    super(props);

    this.state = {
      movies: [],
      name: '',
      fetching: false
    }
  }

  onSearch = () => {
    
  }

  onTextChange = name => e => {
    this.setState({ [name]: e.target.value });
  }

  render() {
    const { ...other } = this.props;
    const { name, fetching } = this.state;

    return (
      <Dialog {...other}>
        <DialogTitle>Search</DialogTitle>
        <DialogContent>
          <TextField
            autoFocus
            margin="normal"
            label="Name"
            value={name}
            onChange={this.onTextChange('name')}
            fullWidth
          />
        </DialogContent>
        <DialogActions>
          <Button onClick={() => this.props.onClose()} color="primary">
            Cancel
          </Button>
          <Button onClick={() => this.onSearch()} color="primary" disabled={fetching}>
            Search
          </Button>
        </DialogActions>
      </Dialog>
    )
  }
}