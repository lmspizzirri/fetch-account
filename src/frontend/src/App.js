import './App.css';

import { Route, Routes, BrowserRouter, Navigate } from 'react-router-dom';
import React from 'react';
import Interface from './page/Interface';
import Login from './page/Login';
import isAuthenticated from './service/authService';

class App extends React.Component {
  render() {
    return (
      <BrowserRouter>
        <Routes>
          <Route
            path='/'
            element={<Login/>}
          />
          <Route
            path='/painel'
            element={isAuthenticated() ? <Interface /> : <Navigate to="/login" />}
          />
        </Routes>
      </BrowserRouter>
    );
  }
}

export default App;
