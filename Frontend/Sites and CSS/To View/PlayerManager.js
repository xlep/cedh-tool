import React, {useEffect, useState} from 'react';

function PlayerManager() {
  const tournamentId = 'e29fbe3f-1755-43cc-a27a-393ec6d80a09';
  const [players, setPlayers] = useState([]);
  const [checkedIn, setCheckedIn] = useState({});
  const [editingDecklist, setEditingDecklist] = useState({});
  const [decklistDrafts, setDecklistDrafts] = useState({});
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [savingDecklist, setSavingDecklist] = useState({}); // Track which decklist is saving
  const [droppingPlayer, setDroppingPlayer] = useState({}); // Track dropping state per player

  useEffect(() => {
    async function fetchPlayers() {
      try {
        const response = await fetch(
            `http://localhost:8080/api/v1/tournament/players/${tournamentId}`);
        if (!response.ok) {
          throw new Error(`Failed to load players: ${response.statusText}`);
        }
        const data = await response.json();
        setPlayers(data);
      } catch (err) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    }

    fetchPlayers();
  }, [tournamentId]);

  const toggleCheckIn = (playerId) => {
    setCheckedIn((prev) => ({
      ...prev,
      [playerId]: !prev[playerId],
    }));
  };

  const handleDrop = async (playerId) => {
    if (!window.confirm(
        'Are you sure you want to drop this player from the tournament?')) {
      return;
    }
    setDroppingPlayer((prev) => ({...prev, [playerId]: true}));
    try {
      const res = await fetch(
          `http://localhost:8080/api/v1/tournament/players/${tournamentId}/drop/${playerId}`,
          {
            method: 'POST',
          });
      if (!res.ok) {
        throw new Error('Failed to drop player');
      }
      setPlayers((prev) => prev.filter((p) => p.playerId !== playerId));
      setCheckedIn((prev) => {
        const updated = {...prev};
        delete updated[playerId];
        return updated;
      });
      setEditingDecklist((prev) => {
        const updated = {...prev};
        delete updated[playerId];
        return updated;
      });
      setDecklistDrafts((prev) => {
        const updated = {...prev};
        delete updated[playerId];
        return updated;
      });
      alert('Player dropped successfully.');
    } catch (e) {
      alert('Error dropping player.');
      console.error(e);
    } finally {
      setDroppingPlayer((prev) => {
        const updated = {...prev};
        delete updated[playerId];
        return updated;
      });
    }
  };

  const startEditingDecklist = (playerId) => {
    const current = decklistDrafts[playerId] ?? '';
    setEditingDecklist((prev) => ({...prev, [playerId]: true}));
    setDecklistDrafts((prev) => ({...prev, [playerId]: current}));
  };

  const saveDecklist = async (playerId) => {
    setSavingDecklist((prev) => ({...prev, [playerId]: true}));
    try {
      // TODO: Replace with actual API call to save decklist
      await fetch(
          `http://localhost:8080/api/v1/tournament/players/${tournamentId}/decklist/${playerId}`,
          {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({decklist: decklistDrafts[playerId]}),
          });
      setEditingDecklist((prev) => ({...prev, [playerId]: false}));
      alert('Decklist saved.');
    } catch (e) {
      alert('Failed to save decklist.');
      console.error(e);
    } finally {
      setSavingDecklist((prev) => ({...prev, [playerId]: false}));
    }
  };

  const cancelEditing = (playerId) => {
    setEditingDecklist((prev) => ({...prev, [playerId]: false}));
  };

  const handleDecklistChange = (playerId, value) => {
    setDecklistDrafts((prev) => ({...prev, [playerId]: value}));
  };

  const lockPlayerToTable = (playerId) => {
    const table = prompt('Enter the table to lock this player to:');
    if (table !== null && table.trim() !== '') {
      alert(`Player ${playerId} locked to table: ${table}`);
      // TODO: Add your logic to save/store the locked table info here
    } else {
      alert('Table lock cancelled or invalid input.');
    }
  };

  return (
      <div style={{padding: '0 10px'}}>
        {loading && <p>Loading players...</p>}
        {error && <p style={{color: 'red'}}>Error: {error}</p>}

        {!loading && !error && players.length === 0 && (
            <p className="no-results">No players found.</p>
        )}

        {!loading && !error && players.length > 0 && (
            <>
              <div
                  className="total-players"
                  style={{
                    fontWeight: 'bold',
                    color: '#ff7f11',
                    marginBottom: '0.5rem',
                    textAlign: 'right'
                  }}
              >
                Total Players: {players.length}
              </div>
              <table className="players-table">
                <thead>
                <tr>
                  <th>Player</th>
                  <th>Email</th>
                  <th>Decklist</th>
                  <th>Status</th>
                  <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                {players.map((player) => {
                  const playerId = player.playerId;
                  const isCheckedIn = checkedIn[playerId];
                  const isEditing = editingDecklist[playerId];
                  const decklist = decklistDrafts[playerId] ?? '';
                  const isSaving = savingDecklist[playerId];
                  const isDropping = droppingPlayer[playerId];

                  return (
                      <tr key={playerId}>
                        <td>{player.firstname} {player.lastname}</td>
                        <td>{player.email}</td>
                        <td>
                          {isEditing ? (
                              <>
                                <input
                                    type="text"
                                    value={decklist}
                                    onChange={(e) => handleDecklistChange(
                                        playerId, e.target.value)}
                                    style={{width: '100%'}}
                                    disabled={isSaving}
                                />
                                <div style={{marginTop: '4px'}}>
                                  <button onClick={() => saveDecklist(playerId)}
                                          disabled={isSaving}>
                                    {isSaving ? 'Saving...' : 'Save'}
                                  </button>
                                  <button
                                      onClick={() => cancelEditing(playerId)}
                                      style={{marginLeft: '4px'}}
                                      disabled={isSaving}>
                                    Cancel
                                  </button>
                                </div>
                              </>
                          ) : (
                              decklist || '—'
                          )}
                        </td>
                        <td>
                      <span className={`tag ${isCheckedIn ? 'checked-in'
                          : 'sign-up'}`}>
                        {isCheckedIn ? 'Checked In' : 'Sign-up'}
                      </span>
                        </td>
                        <td>
                          <button onClick={() => toggleCheckIn(playerId)}
                                  disabled={isDropping}>
                            {isCheckedIn ? 'Uncheck' : 'Check In'}
                          </button>
                          <button
                              onClick={() => startEditingDecklist(playerId)}
                              style={{marginLeft: '8px'}}
                              disabled={isDropping || isEditing}
                          >
                            Modify Decklist
                          </button>
                          <button
                              onClick={() => lockPlayerToTable(playerId)}
                              style={{marginLeft: '8px'}}
                              disabled={isDropping}
                          >
                            Lock Player to Table
                          </button>
                          <button
                              onClick={() => handleDrop(playerId)}
                              style={{marginLeft: '8px', color: 'red'}}
                              disabled={isDropping}
                          >
                            {isDropping ? 'Dropping...' : 'Drop'}
                          </button>
                        </td>
                      </tr>
                  );
                })}
                </tbody>
              </table>
            </>
        )}
      </div>
  );
}

export default PlayerManager;
