import React, { useState } from 'react';
import { loginUser } from '../api/UserApi';
import { useNavigate } from 'react-router-dom';

const LoginPage = ({ onLogin }) => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError(null);

    try {
      const success = await loginUser(username, password);
      if (success) {
        onLogin(username);
      } else {
        setError('Invalid username or password');
      }
    } catch (err) {
      setError('Login failed. Please try again.');
      console.error(err);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div
      style={{
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        marginTop: '5rem',
        gap: '1.5rem',
      }}
    >
      <h1 className="title">cEDH TOol</h1>  {/* big orange heading */}
      <form onSubmit={handleSubmit} style={{ width: '300px' }}>
        <h2 className="section-title">Login</h2>  {/* smaller orange heading */}

        <input
          type="text"
          placeholder="Username"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
          required
          autoFocus
          style={{ width: '280px', marginBottom: '1rem', padding: '0.5rem' }}
        />

        <input
          type="password"
          placeholder="Password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          required
          style={{ width: '280px', marginBottom: '1rem', padding: '0.5rem' }}
        />

        <button
          type="submit"
          disabled={loading}
          style={{ width: '100%', padding: '0.5rem' }}
        >
          {loading ? 'Logging in...' : 'Login'}
        </button>

        {error && <div style={{ color: 'red', marginTop: '1rem' }}>{error}</div>}
      </form>
      <button
        type="button"
        className="nav-btn"
        onClick={() => navigate('/bracket')}
        style={{ marginTop: '1rem' }}
      >
        View Bracket
      </button>
      <button
        type="button"
        className="nav-btn"
        onClick={() => navigate('/scores')}
        style={{ marginTop: '1rem' }}
      >
        View Player Scores
      </button>
    </div>
  );
};

export default LoginPage;
