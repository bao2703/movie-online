import React, { Component } from 'react';
import * as genreService from '../services/genre.service';

import Button from 'material-ui/Button';
import TextField from 'material-ui/TextField';
import Table, { TableBody, TableCell, TableHead, TableRow } from 'material-ui/Table';
import Dialog, { DialogActions, DialogContent, DialogContentText, DialogTitle} from 'material-ui/Dialog';

export class Genre extends Component {
  constructor(props) {
    super(props);

    this.state = {
      genres: [],
      name: '',
      description: '',
      open: false
    }
  }

  handleClickOpen = () => {
    this.setState({ open: true });
  };

  handleClose = () => {
    this.setState({ open: false });
  };
        
  componentDidMount() {
    genreService.fetch().then(genres => {
      this.setState({ genres })
    });
  }

  onTextChange = name => e => {
    this.setState({ [name]: e.target.value });
  }

  add = () => {
    const genre = {name: this.state.name};
    genreService.create(genre).then(() => {
      this.componentDidMount();
    })
  }

  edit = () => {
    
    this.handleClose();
  }

  delete = () => {
    
    this.handleClose();
  }

  render() {
    const { genres } = this.state;

    return (
      <div>
        <input onChange={this.onTextChange('name')} />
        <button onClick={() => this.add()}>Add</button>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell>Id</TableCell>
              <TableCell>Name</TableCell>
              <TableCell>Description</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {genres.map(genre =>
              <TableRow key={genre.id} onClick={this.handleClickOpen}>
                <TableCell>{genre.id}</TableCell>
                <TableCell>{genre.name}</TableCell>
                <TableCell>{genre.description}</TableCell>
              </TableRow>
            )}
          </TableBody>
        </Table>

        <Dialog
          open={this.state.open}
          onClose={this.handleClose}
          aria-labelledby="form-dialog-title"
        >
          <DialogTitle id="form-dialog-title">Edit</DialogTitle>
          <DialogContent>
            <TextField
              autoFocus
              margin="dense"
              id="name"
              label="Name"
              type="text"
              value=""
              fullWidth
            />
            <TextField
              margin="dense"
              id="description"
              label="Description"
              type="text"
              value=""
              fullWidth
            />
          </DialogContent>
          <DialogActions>
            <Button onClick={this.handleClose} color="primary">
              Cancel
            </Button>
            <Button onClick={this.delete} color="primary">
              Delete
            </Button>
            <Button onClick={this.edit} color="primary">
              Save
            </Button>
          </DialogActions>
        </Dialog>
      </div>
    )
  }  
}

export default Genre