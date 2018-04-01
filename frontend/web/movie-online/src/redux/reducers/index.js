import { combineReducers } from 'redux';

import { routerReducer } from 'react-router-redux';
import { AuthReducer } from './auth.reducer';

export const RootReducer = combineReducers({
  auth: AuthReducer,
  router: routerReducer
});
