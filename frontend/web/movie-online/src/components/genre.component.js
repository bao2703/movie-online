import React, { Component } from 'react';
import * as genreService from '../services/genre.service';

import Button from 'material-ui/Button';
import TextField from 'material-ui/TextField';
import Table, { TableBody, TableCell, TableHead, TableRow } from 'material-ui/Table';
import Dialog, { DialogActions, DialogContent, DialogTitle } from 'material-ui/Dialog';

export class Genre extends Component {
  constructor(props) {
    super(props);

    this.state = {
      genres: [],
      filterGenres: [],
      name: '',
      description: '',
      openAdd: false,
      openEdit: false,
      openSearch: false,
      selectedGenre: { name: '', description: '' }
    }
  }

  openAddDialog = () => {
    this.setState({ openAdd: true });
  }

  closeAddDialog = () => {
    this.setState({ openAdd: false });
    this.fetchGenres();
  }

  openEditDialog = selectedGenre => {
    this.setState({
      openEdit: true,
      selectedGenre
    });
  }

  closeEditDialog = () => {
    this.setState({ openEdit: false });
    this.fetchGenres();
  }

  openSearchDialog = () => {
    this.setState({ openSearch: true });
  }

  closeSearchDialog = () => {
    this.setState({ openSearch: false });
  }

  componentDidMount() {
    this.fetchGenres();
  }

  onTextChange = name => e => {
    this.setState({ [name]: e.target.value });
  }

  fetchGenres = () => {
    genreService.fetchAll().then(genres => {
      this.setState({ genres, filterGenres: genres })
    });
  }

  onSearch = searchString => {
    searchString = searchString.toLowerCase();
    const filterGenres = this.state.genres.filter(item => item.name.toLowerCase().includes(searchString) || item.description.toLowerCase().includes(searchString));
    this.setState({ filterGenres });
    this.closeSearchDialog();
  }

  render() {
    const { filterGenres, selectedGenre } = this.state;

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
              <TableCell>Description</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {filterGenres.map(genre =>
              <TableRow key={genre.id} onClick={() => this.openEditDialog(genre)} className="table-row">
                <TableCell>{genre.name}</TableCell>
                <TableCell>{genre.description}</TableCell>
              </TableRow>
            )}
          </TableBody>
        </Table>

        <AddGenreDialog
          fullWidth
          open={this.state.openAdd}
          onClose={this.closeAddDialog}
        />

        <EditGenreDialog
          fullWidth
          open={this.state.openEdit}
          onClose={this.closeEditDialog}
          genre={selectedGenre}
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

export default Genre

class AddGenreDialog extends Component {

  constructor(props) {
    super(props);

    this.state = {
      name: '',
      description: '',
      fetching: false
    };
  }

  componentWillReceiveProps(nextProps) {
    this.setState({
      name: '',
      description: '',
      fetching: false
    });
  }

  onAdd = () => {
    this.setState({ fetching: true });
    const { name, description } = this.state;
    genreService.create({ name, description }).then(() => {
      this.props.onClose();
      this.setState({ fetching: false });
    });
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

class EditGenreDialog extends Component {

  constructor(props) {
    super(props);

    this.state = {
      id: '',
      name: '',
      description: '',
      fetching: false
    }
  }

  componentWillReceiveProps(nextProps) {
    const { genre } = nextProps;
    this.setState({
      id: genre.id,
      name: genre.name,
      description: genre.description
    });
  }

  onEdit = () => {
    this.setState({ fetching: true });
    const { id, name, description } = this.state;
    genreService.edit(id, { name, description }).then(() => {
      this.props.onClose();
      this.setState({ fetching: false });
    })
  }

  onRemove = () => {
    this.setState({ fetching: true });
    const { id } = this.state;
    genreService.remove(id).then(() => {
      this.props.onClose();
      this.setState({ fetching: false });
    });
  }

  onTextChange = name => e => {
    this.setState({ [name]: e.target.value });
  }

  render() {
    const { ...other } = this.props;
    const { name, description, fetching } = this.state;

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
