import React, { Component } from 'react';
import { Link } from 'react-router-dom';

import * as movieService from '../services/movie.service';
import * as genreService from '../services/genre.service';

import Button from 'material-ui/Button';
import TextField from 'material-ui/TextField';
import Table, { TableBody, TableCell, TableHead, TableRow } from 'material-ui/Table';
import Dialog, { DialogActions, DialogContent, DialogTitle } from 'material-ui/Dialog';
import Input from 'material-ui/Input';

import Select from 'material-ui/Select';
import { MenuItem } from 'material-ui/Menu';

export class Movie extends Component {

  constructor(props) {
    super(props);

    this.state = {
      movies: [],
      filterMovies: [],
      name: '',
      description: '',
      openAdd: false,
      openEdit: false,
      openSearch: false,
      selectedMovie: { name: '', description: '', genres: [] }
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
      this.setState({ movies, filterMovies: movies });
    });
  }

  onSearch = searchString => {
    searchString = searchString.toLowerCase();
    const filterMovies = this.state.movies.filter(item => item.name.toLowerCase().includes(searchString) || item.description.toLowerCase().includes(searchString));
    this.setState({ filterMovies });
    this.closeSearchDialog();
  }

  render() {
    const { filterMovies, selectedMovie } = this.state;

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
              <TableCell>Genres</TableCell>
              <TableCell>Description</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {filterMovies.map(movie =>
              <TableRow key={movie.id} onClick={() => this.openEditDialog(movie)} className="table-row">
                <TableCell>
                  <Link to={`/movie/${movie.id}`}>{movie.name}</Link>
                </TableCell>
                <TableCell>{movie.views}</TableCell>
                <TableCell>
                  {movie.genres.map((genre, index) =>
                    <span key={genre.id}>{genre.name}{index < movie.genres.length - 1 && ', '}</span>
                  )}
                </TableCell>
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
          onSearch={this.onSearch}
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
      fetching: false,
      selectedGenres: [],
      genres: []
    }
    this.fetchGenres();
  }

  componentWillReceiveProps(nextProps) {
    this.setState({
      name: '',
      description: '',
      fetching: false,
      selectedGenres: []
    });
  }

  onAdd = () => {
    this.setState({ fetching: true });
    const { name, description, file, selectedGenres } = this.state;
    movieService.create({ name, description, file, selectedGenres }).then(() => {
      this.props.onClose();
      this.setState({ fetching: false });
    });
  }

  fetchGenres = () => {
    genreService.fetchAll().then(genres => {
      this.setState({ genres });
    });
  }

  handleFileChange = e => {
    e.preventDefault();
    const file = e.target.files[0];
    this.setState({ file });
  }

  handleChange = event => {
    this.setState({ selectedGenres: event.target.value });
  }

  onTextChange = name => e => {
    this.setState({ [name]: e.target.value });
  }

  render() {
    const { ...other } = this.props;
    const { name, description, fetching, selectedGenres, genres } = this.state;

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
          <Select className="my-3"
            multiple
            value={selectedGenres}
            onChange={this.handleChange}
            input={<Input />}
            style={{ width: 552 }}
          >
            {genres.map(genre =>
              <MenuItem key={genre.id} value={genre.id}>
                {genre.name}
              </MenuItem>
            )}
          </Select>
          <div align="center">
            <input type="file" className="mt-3" onChange={this.handleFileChange} />
          </div>
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
      fetching: false,
      selectedGenres: [],
      genres: []
    }
    this.fetchGenres();
  }

  componentWillReceiveProps(nextProps) {
    const { movie } = nextProps;
    this.setState({
      id: movie.id,
      name: movie.name,
      posterUrl: 'http://localhost:5000' + movie.posterUrl,
      description: movie.description,
      selectedGenres: movie.genres.map(g => g.id)
    });
  }

  onEdit = () => {
    this.setState({ fetching: true });
    const { id, name, description, file, selectedGenres } = this.state;
    movieService.edit(id, { name, description, file, selectedGenres }).then(() => {
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

  fetchGenres = () => {
    genreService.fetchAll().then(genres => {
      this.setState({ genres });
    });
  }

  handleFileChange = e => {
    e.preventDefault();
    const file = e.target.files[0];
    this.setState({ file });
  }

  handleChange = event => {
    this.setState({ selectedGenres: event.target.value });
  }

  onTextChange = name => e => {
    this.setState({ [name]: e.target.value });
  }

  render() {
    const { ...other } = this.props;
    const { name, posterUrl, description, fetching, genres, selectedGenres } = this.state;

    return (
      <Dialog {...other}>
        <DialogTitle>Edit</DialogTitle>
        <DialogContent>
          <div align="center">
            <img src={posterUrl} className="img-fluid" alt="" />
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
          <Select className="my-3"
            multiple
            value={selectedGenres}
            onChange={this.handleChange}
            input={<Input />}
            style={{ width: 552 }}
          >
            {genres.map(genre =>
              <MenuItem key={genre.id} value={genre.id}>
                {genre.name}
              </MenuItem>
            )}
          </Select>
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
      key: '',
    }
  }

  handleKeyPress = (e) => {
    if (e.key === 'Enter') {
      this.props.onSearch(this.state.key);
    }
  }

  onTextChange = name => e => {
    this.setState({ [name]: e.target.value });
  }

  render() {
    const { onSearch, ...other } = this.props;
    const { key } = this.state;

    return (
      <Dialog {...other}>
        <DialogTitle>Search</DialogTitle>
        <DialogContent>
          <TextField
            autoFocus
            margin="normal"
            label="Key"
            value={key}
            onChange={this.onTextChange('key')}
            onKeyPress={this.handleKeyPress}
            fullWidth
          />
        </DialogContent>
        <DialogActions>
          <Button onClick={() => this.props.onClose()} color="primary">
            Cancel
          </Button>
          <Button onClick={() => onSearch(key)} color="primary">
            Search
          </Button>
        </DialogActions>
      </Dialog>
    )
  }
}