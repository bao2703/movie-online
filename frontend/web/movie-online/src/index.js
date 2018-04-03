import React from 'react';
import ReactDOM from 'react-dom';
import registerServiceWorker from './registerServiceWorker';

import { Provider } from 'react-redux';
import { ConfigureStore } from './redux/store';

import App from './containers/app.component';

import 'bootstrap/dist/css/bootstrap.min.css';
import './index.css';

const store = ConfigureStore();

ReactDOM.render(
  <Provider store={store}>
    <App />
  </Provider>,
  document.getElementById('root')
);
registerServiceWorker();
