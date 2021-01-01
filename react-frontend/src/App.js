import React from 'react';
import { Route } from 'react-router-dom';
import BoardListPage from './pages/BoardListPage';
import LoginPage from './pages/LoginPage';
import ReadPage from './pages/ReadPage';
import RegisterPage from './pages/RegisterPage';
import WritePage from './pages/WritePage';

const App = () => {
  return (
    <>
      <Route component={BoardListPage} path={['/@:username', '/']} exact />
      <Route component={LoginPage} path="/login" />
      <Route component={RegisterPage} path="/register" />
      <Route component={WritePage} path="/write" />
      <Route component={ReadPage} path="/@:username/:postId" />
    </>
  );
};

export default App;
