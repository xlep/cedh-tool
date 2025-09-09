import React, { useState, useEffect } from 'react';
import { Routes, Route, Navigate, useNavigate } from 'react-router-dom';
import PlayerManager from './PlayerManager';
import TournamentManager from './TournamentManager';
import Bracket from './Bracket';
import LoginPage from './components/LoginPage';
import { ResultsProvider } from './ResultsContext';
import './App.css';  // or wherever your CSS file is
import PlayerScores from './components/PlayerScores';

function App() {
  const navigate = useNavigate();

  const [loggedIn, setLoggedIn] = useState(() => localStorage.getItem('loggedIn') === 'true');
  const [username, setUsername] = useState(() => localStorage.getItem('username') || '');

  const handleLogin = (username) => {
    setUsername(username);
    setLoggedIn(true);
    localStorage.setItem('loggedIn', 'true');
    localStorage.setItem('username', username);
    navigate('/playerManager');
  };

  const handleLogout = () => {
    setLoggedIn(false);
    setUsername('');
    localStorage.removeItem('loggedIn');
    localStorage.removeItem('username');
    navigate('/login');
  };

  return (
    <ResultsProvider>
      <div className="app">
        {loggedIn && (
          <nav className="top-nav">
            <h1 className="nav-title">cEDH TOol</h1>
            <div className="nav-buttons">
              <button className="nav-btn" onClick={() => navigate('/PlayerManager')}>
                Player Manager
              </button>
              <button className="nav-btn" onClick={() => navigate('/TournamentManager')}>
                Tournament Manager
              </button>
              <button className="nav-btn" onClick={() => navigate('/Bracket')}>
                Bracket
              </button>
              <button className="nav-btn" onClick={() => navigate('/scores')}>
                Player Scores
              </button>
              <button className="nav-btn" onClick={handleLogout}>
                Logout
              </button>
            </div>
          </nav>
        )}

        <main className="main-content">
          <Routes>
            <Route
              path="/login"
              element={loggedIn ? <Navigate to="/playerManager" /> : <LoginPage onLogin={handleLogin} />}
            />
            <Route
              path="/playerManager"
              element={loggedIn ? <PlayerManager /> : <Navigate to="/login" />}
            />
            <Route
              path="/tournamentManager"
              element={loggedIn ? <TournamentManager /> : <Navigate to="/login" />}
            />
            <Route
              path="/bracket"
              element={<Bracket />}
            />
            <Route path="/scores" element={<PlayerScores />} />
            <Route
              path="/"
              element={<Navigate to={loggedIn ? "/playerManager" : "/login"} />}
            />
            <Route
              path="*"
              element={<div style={{ color: '#aaa' }}>Page Not Found</div>}
            />
          </Routes>
        </main>
      </div>
    </ResultsProvider>
  );
}

export default App;
