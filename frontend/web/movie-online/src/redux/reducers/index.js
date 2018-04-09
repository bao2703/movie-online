import { combineReducers } from 'redux';

import { routerReducer } from 'react-router-redux';
import { authReducer } from './auth.reducer';

export const RootReducer = combineReducers({
  auth: authReducer,
  router: routerReducer
});
