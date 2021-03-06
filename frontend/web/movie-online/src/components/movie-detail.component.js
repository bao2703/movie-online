import React, { Component } from 'react';

import * as movieService from '../services/movie.service';
import * as episodeService from '../services/episode.service';

import Button from 'material-ui/Button';
import TextField from 'material-ui/TextField';
import Dialog, { DialogActions, DialogContent, DialogTitle } from 'material-ui/Dialog';

export class MovieDetail extends Component {

  constructor(props) {
    super(props);

    this.state = {
      movie: null,
      episodes: [],
      openEdit: false,
      openAdd: false,
      selectedEpisode: { name: '' },
    }
  }

  componentDidMount() {
    this.fetchMovie();
    this.fetchEpisodes();
  }

  fetchMovie = () => {
    movieService.fetch(this.props.match.params.id).then(movie => {
      this.setState({ movie });
    });
  }

  fetchEpisodes = () => {
    movieService.fetchEpisodes(this.props.match.params.id).then(episodes => {
      this.setState({ episodes });
    });
  }

  openAddDialog = () => {
    this.setState({ openAdd: true });
  }

  closeAddDialog = () => {
    this.setState({ openAdd: false });
    this.fetchEpisodes();
  }

  openEditDialog = selectedEpisode => {
    this.setState({
      openEdit: true,
      selectedEpisode
    });
  }

  closeEditDialog = () => {
    this.setState({ openEdit: false });
    this.fetchEpisodes();
  }

  render() {
    const { movie, episodes, selectedEpisode } = this.state;

    return (
      <div>
        {movie &&
          <div className="row">
            <div className="col-3" align="center">
              <img className="img-fluid" src={'http://localhost:5000' + movie.posterUrl} alt="" />
            </div>
            <div className="col-9">
              <h3>{movie.name}</h3>
              <div>{movie.description}</div>
              <div className="mt-3">
                <Button color="primary" variant="raised" onClick={() => this.openAddDialog()}>
                  Add Episodes
                </Button>
              </div>
            </div>
          </div>
        }

        <div className="mt-3"></div>

        {episodes.map(episode =>
          <span key={episode.id} onClick={() => this.openEditDialog(episode)} className="px-1">
            <button className="btn btn-primary">{episode.name}</button>
          </span>
        )}

        {movie &&
          <AddEpisodeDialog
            fullWidth
            maxWidth="md"
            open={this.state.openAdd}
            onClose={this.closeAddDialog}
            movieId={movie.id}
          />
        }

        <EditEpisodeDialog
          fullWidth
          maxWidth="md"
          open={this.state.openEdit}
          onClose={this.closeEditDialog}
          episode={selectedEpisode}
        />
      </div>
    )
  }
}

export default MovieDetail

class AddEpisodeDialog extends Component {

  constructor(props) {
    super(props);

    this.state = {
      name: '',
      file: null,
      fetching: false
    }
  }

  componentWillReceiveProps(nextProps) {
    this.setState({
      name: '',
      file: null,
      fetching: false,
      completed: 0
    })
  }

  onAdd = () => {
    this.setState({ fetching: true });
    const { movieId } = this.props;
    const { name, file } = this.state;
    const onUploadProgress = progressEvent => {
      this.setState({ completed: (progressEvent.loaded / progressEvent.total * 100) - 5 });
    };
    movieService.addEpisode(movieId, { name, file }, onUploadProgress).then(() => {
      this.props.onClose();
      this.setState({ fetching: false });
    });
  }

  onTextChange = name => e => {
    this.setState({ [name]: e.target.value });
  }

  handleFileChange = e => {
    e.preventDefault();
    const file = e.target.files[0];
    this.setState({ file });
  }

  render() {
    const { movieId, ...other } = this.props;
    const { name, fetching, completed } = this.state;

    return (
      <Dialog {...other}>
        <DialogTitle>Add</DialogTitle>
        <DialogContent>
          <div className="row">
            <div className="col-6">
              <TextField
                autoFocus
                margin="normal"
                label="Name"
                value={name}
                onChange={this.onTextChange('name')}
                fullWidth
              />
            </div>
            <div className="col-6">
              <div align="center" className="mt-3">
                <input type="file" className="mt-3" onChange={this.handleFileChange} />
              </div>
            </div>
          </div>
          {completed !== 0 &&
            <div className="progress mt-4">
              <div className="progress-bar" style={{ width: `${completed}%` }}></div>
            </div>
          }
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

class EditEpisodeDialog extends Component {
  constructor(props) {
    super(props);

    this.state = {
      id: '',
      name: '',
      fetching: false,
      completed: 0
    }
  }

  componentWillReceiveProps(nextProps) {
    const { episode } = nextProps;
    this.setState({
      id: episode.id,
      name: episode.name,
      url: 'http://localhost:5000' + episode.url,
      completed: 0
    });
  }

  onEdit = () => {
    this.setState({ fetching: true, url: '' });
    const { id, name, file } = this.state;
    const onUploadProgress = progressEvent => {
      this.setState({ completed: (progressEvent.loaded / progressEvent.total * 100) - 5 });
    };
    episodeService.edit(id, { name, file }, onUploadProgress).then(() => {
      this.props.onClose();
      this.setState({ fetching: false });
    });
  }

  onRemove = () => {
    this.setState({ fetching: true });
    const { id } = this.state;
    episodeService.remove(id).then(() => {
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
    const { name, url, fetching, completed } = this.state;

    return (
      <Dialog {...other}>
        <DialogTitle>Edit</DialogTitle>
        <DialogContent>
          <div className="row">
            <div className="col-6">
              <TextField
                autoFocus
                margin="normal"
                label="Name"
                value={name}
                onChange={this.onTextChange('name')}
                fullWidth
              />
            </div>
            <div className="col-6">
              <div align="center" className="mt-3">
                <input type="file" className="mt-3" onChange={this.handleFileChange} />
              </div>
            </div>
          </div>
          {completed !== 0 ? (
            <div className="progress mt-4">
              <div className="progress-bar" style={{ width: `${completed}%` }}></div>
            </div>
          ) : (
              <div className="embed-responsive embed-responsive-16by9">
                <iframe className="embed-responsive-item" src={url} title={name}></iframe>
              </div>
            )}
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
