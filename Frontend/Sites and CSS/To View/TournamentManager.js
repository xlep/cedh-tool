import React, { useEffect, useRef, useState } from 'react';
import './App.css';

function TournamentManager() {
  const [podsData, setPodsData] = useState([]);
  const [loadingRound, setLoadingRound] = useState(false);
  const [rounds, setRounds] = useState([]);
  const [selectedRoundIndex, setSelectedRoundIndex] = useState(0);
  const [searchTerm, setSearchTerm] = useState('');
  const ws = useRef(null);
  const MAX_ROUNDS = 5;
  const tournamentId = "e29fbe3f-1755-43cc-a27a-393ec6d80a09";

  useEffect(() => {
    ws.current = new WebSocket('ws://localhost:8080');
    ws.current.onmessage = (event) => {
      const message = JSON.parse(event.data);
      if (message.type === 'pods') {
        setPodsData(message.data);
      }
    };
    return () => ws.current && ws.current.close();
  }, []);

  useEffect(() => {
    async function fetchRounds() {
      try {
        const res = await fetch(`http://localhost:8080/api/v1/tournaments/${tournamentId}/rounds`);
        if (!res.ok) throw new Error('Failed to fetch rounds');
        const data = await res.json();
        setRounds(data);
      } catch (err) {
        console.error(err);
      }
    }
    fetchRounds();
  }, [tournamentId]);

  const handleNewRound = async () => {
    if (rounds.length >= MAX_ROUNDS) {
      alert(`Maximum number of rounds (${MAX_ROUNDS}) reached.`);
      return;
    }
    setLoadingRound(true);
    try {
      const res = await fetch(`http://localhost:8080/api/v1/tournaments/${tournamentId}/rounds`, {
        method: 'POST',
        headers: { 'Accept': '*/*' },
      });
      if (!res.ok) throw new Error('Failed to generate new round');
      alert('New round generated successfully');
      const newRounds = await fetch(`http://localhost:8080/api/v1/tournaments/${tournamentId}/rounds`).then(res => res.json());
      setRounds(newRounds);
      setSelectedRoundIndex(newRounds.length - 1);
    } catch (err) {
      console.error(err);
      alert('Error generating new round');
    } finally {
      setLoadingRound(false);
    }
  };

  const handleTopCut = () => {
    alert('Cut To Top 10 button clicked');
    // TODO: Implement top cut logic
  };

  // Filter pods by searchTerm matching firstname + lastname (case-insensitive)
  const filteredPods =
    rounds[selectedRoundIndex]?.pods?.filter((pod) =>
      pod.seats.some((seat) => {
        const fullName = `${seat.firstname} ${seat.lastname}`.toLowerCase();
        return fullName.includes(searchTerm.toLowerCase());
      })
    ) || [];

  return (
    <div style={{ padding: '0 10px' }}>
      <h2>Tournament Manager</h2>
      <p>This section allows you to manage rounds, top cut, and other features.</p>

      <div style={{ marginTop: '1rem', marginBottom: '1rem' }}>
        <button
          onClick={handleNewRound}
          disabled={loadingRound || rounds.length >= MAX_ROUNDS}
          style={{ marginRight: '10px' }}
        >
          {loadingRound ? 'Generating...' : 'Generate New Round'}
        </button>
        <button onClick={handleTopCut}>Cut to Top 10</button>
      </div>

      {/* Search bar at top */}
      <div className="search-bar-container">
        <input
          type="text"
          placeholder="Search for a player..."
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
          className="search-bar"
        />
      </div>

      <div style={{ marginBottom: '1rem' }}>
        <h3>All Rounds</h3>
        {rounds.length > 0 ? (
          <>
            {/* Round Tabs */}
            <div className="nav-buttons" style={{ marginBottom: '1rem' }}>
              {rounds.map((round, index) => (
                <button
                  key={round.round}
                  onClick={() => setSelectedRoundIndex(index)}
                  className={`nav-btn ${index === selectedRoundIndex ? 'active' : ''}`}
                  type="button"
                >
                  Round {round.round}
                </button>
              ))}
            </div>

            {/* Pods grid */}
            <div className="pods-grid">
              {(searchTerm ? filteredPods : rounds[selectedRoundIndex].pods).map((pod) => (
                <div key={pod.podId} className="pod">
                  <h2>Pod {pod.podName}</h2>
                  <ul>
                    {pod.seats
                      .slice()
                      .sort((a, b) => a.seat - b.seat)
                      .map((seat) => (
                        <li key={seat.playerId}>
                          {seat.firstname} {seat.lastname} (Seat {seat.seat})
                        </li>
                      ))}
                  </ul>
                </div>
              ))}
              {(searchTerm && filteredPods.length === 0) && (
                <p className="no-results">No players found.</p>
              )}
            </div>
          </>
        ) : (
          <p>No rounds available.</p>
        )}
      </div>
    </div>
  );
}

export default TournamentManager;
