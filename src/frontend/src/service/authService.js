import { jwtDecode } from 'jwt-decode';

const isAuthenticated = () => {
  const token = localStorage.getItem('token');
  if (!token) {
    return false;
  }

  try {
    const decodedToken = jwtDecode(token);
    return true;
  } catch (error) {
    return false;
  }
};

export default isAuthenticated;
