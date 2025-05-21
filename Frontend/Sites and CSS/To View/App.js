import React, { useState } from 'react';
import './App.css';
import PlayerManager from './PlayerManager';
import TournamentManager from './TournamentManager';

function App() {
  const [activeView, setActiveView] = useState('playerManager');

  const renderContent = () => {
    switch (activeView) {
      case 'playerManager':
        return <PlayerManager />;
      case 'tournamentManager':
        return <TournamentManager />;
      default:
        return <div style={{ color: '#aaa' }}>Coming Soon...</div>;
    }
  };

  return (
    <div className="app">
      <nav className="top-nav">
        <h1 className="nav-title">cEDH TOol</h1>
        <div className="nav-buttons">
          <button
            className={activeView === 'playerManager' ? 'nav-btn active' : 'nav-btn'}
            onClick={() => setActiveView('playerManager')}
          >
            Player Manager
          </button>
          <button
            className={activeView === 'tournamentManager' ? 'nav-btn active' : 'nav-btn'}
            onClick={() => setActiveView('tournamentManager')}
          >
            Tournament Manager
          </button>
          <button className="nav-btn">Penalties</button>
          <button className="nav-btn">Configuration</button>
          <button className="nav-btn">Staff Manager</button>
          <button className="nav-btn">Event Page</button>
        </div>
      </nav>

      <main className="main-content">
        {renderContent()}
      </main>
    </div>
  );
}

export default App;
