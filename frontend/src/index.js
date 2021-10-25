import React from 'react';
import ReactDOM from 'react-dom';
import reportWebVitals from './reportWebVitals';

import {applyMiddleware, compose, createStore} from "redux";
import {createStateSyncMiddleware} from "redux-state-sync";
import thunk from "redux-thunk";

import rootReducer from './store/reducers';
import './index.css';
import App from './App';
import {Provider} from "react-redux";

// ------------------ Redux Declaration ---------------------
const composeEnhancers = typeof window === 'object' && window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ ?
  window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__({
    // name, actionsBlacklist, actionsCreators, serialize ...
  }) : compose;

const middleware = [
  thunk,
  createStateSyncMiddleware({})
];

const enhancer = composeEnhancers(applyMiddleware(...middleware));
const store = createStore(rootReducer, enhancer);
// ------------------ Redux Declaration ---------------------

ReactDOM.render(
  <React.StrictMode>
    <Provider store={store}>
      <App/>
    </Provider>
  </React.StrictMode>,
  document.getElementById('root')
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
