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
      name: '',
      description: '',
      open: false,
      selectedGenre: { name: '', description: '' }
    }
  }

  openDialog = selectedGenre => {
    this.setState({
      open: true,
      selectedGenre
    });
  };

  closeDialog = () => {
    this.setState({ open: false });
    this.fetchGenres();
  };

  componentDidMount() {
    this.fetchGenres();
  }

  onTextChange = name => e => {
    this.setState({ [name]: e.target.value });
  }

  fetchGenres = () => {
    genreService.fetch().then(genres => {
      this.setState({ genres })
    });
  }

  add = () => {
    const genre = { name: this.state.name };
    genreService.create(genre).then(() => {
      this.fetchGenres();
    })
  }

  render() {
    const { genres, selectedGenre } = this.state;

    return (
      <div>
        <input onChange={this.onTextChange('name')} />
        <button onClick={() => this.add()}>Add</button>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell>Name</TableCell>
              <TableCell>Description</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {genres.map(genre =>
              <TableRow key={genre.id} onClick={() => this.openDialog(genre)}>
                <TableCell>{genre.name}</TableCell>
                <TableCell>{genre.description}</TableCell>
              </TableRow>
            )}
          </TableBody>
        </Table>

        <EditGenreDialog
          open={this.state.open}
          onClose={this.closeDialog}
          genre={selectedGenre}
        />
      </div>
    )
  }
}

export default Genre

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