import { applyMiddleware, createStore, compose } from 'redux';

import { createLogger } from 'redux-logger';
import thunk from 'redux-thunk';

import { RootReducer } from '../reducers';

const logger = createLogger();
//const router = routerMiddleware(browserHistory)

export const ConfigureStore = (preloadedState) => {
  const composeEnhancers = window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose;

  return createStore(
    RootReducer,
    preloadedState,
    composeEnhancers(applyMiddleware(thunk, logger))
  );
};
