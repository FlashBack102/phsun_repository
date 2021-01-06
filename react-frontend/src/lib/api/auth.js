import client from './client';

export const login = ({ username, password }) =>
  client.post('/api/auth/login', { username, password });

export const register = ({ username, password }) =>
  client.post('/api/auth/register', null, {
    params: {
      username: username,
      password: password,
    },
  });

export const check = () => client.get('/api/auth/check');

export const hi = () => client.fetch('/api/hi/hello');
