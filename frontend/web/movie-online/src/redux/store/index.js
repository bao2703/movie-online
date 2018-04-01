import { applyMiddleware, createStore, compose } from 'redux';

import { createLogger } from 'redux-logger';
import createHistory from 'history/createBrowserHistory';
import { routerMiddleware } from 'react-router-redux';
import thunk from 'redux-thunk';

import { RootReducer } from '../reducers';

const logger = createLogger();
const history = createHistory();
const router = routerMiddleware(history);

export const ConfigureStore = (preloadedState) => {
  const composeEnhancers = window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose;

  return createStore(
    RootReducer,
    preloadedState,
    composeEnhancers(applyMiddleware(thunk, logger, router))
  );
};

export { history as History };
