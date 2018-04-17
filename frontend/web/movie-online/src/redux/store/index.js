import { applyMiddleware, createStore, compose } from 'redux';

import { createLogger } from 'redux-logger';
import createHistory from 'history/createBrowserHistory';
import { routerMiddleware } from 'react-router-redux';
import thunk from 'redux-thunk';

import { rootReducer } from '../reducers';

const logger = createLogger();
const history = createHistory();
const router = routerMiddleware(history);

export const configureStore = (preloadedState) => {
  const composeEnhancers = window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose;

  return createStore(
    rootReducer,
    preloadedState,
    composeEnhancers(applyMiddleware(thunk, logger, router))
  );
};

export { history };
