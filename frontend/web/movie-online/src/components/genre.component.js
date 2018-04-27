import React, { Component } from 'react';
import * as genreService from '../services/genre.service';

import Table, { TableBody, TableCell, TableHead, TableRow } from 'material-ui/Table';

export class Genre extends Component {
  constructor(props) {
    super(props);

    this.state = {
      genres: [],
      name: ''
    }
  }
        
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

  }

  delete = () => {

  }
      
  onClickItem = () => {
    console.log("Clicked");
  }

  render() {
    const { genres } = this.state;

    return (
      <div>
        <input onChange={this.onTextChange('name')} />
        <button onClick={() => this.add()}>Add</button>
        <button onClick={() => this.edit()}>Edit</button>
        <button onClick={() => this.delete()}>Delete</button>
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
              <TableRow key={genre.id} onClick={() => this.onClickItem()}>
                <TableCell>{genre.id}</TableCell>
                <TableCell>{genre.name}</TableCell>
                <TableCell>{genre.description}</TableCell>
              </TableRow>
            )}
          </TableBody>
        </Table>
      </div>
    )
  }  
}

export default Genre