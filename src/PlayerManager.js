import React, { useEffect, useState } from 'react';
import ActivePlayersTable from './components/ActivePlayersTable';
import DroppedPlayersTable from './components/DroppedPlayersTable';
import DisqualifiedPlayersTable from './components/DisqualifiedPlayersTable';
import {fetchTournamentPlayers, updatePlayerStatus} from './api/TournamentPlayerApi';
import {importPlayers} from './api/TournamentApi';
import * as CsvParserModule from './utils/csvParser';

function PlayerManager() {
  const [tournamentPlayers, setTournamentPlayers] = useState([]);
  // Separate active vs dropped players
  const KEYWORD_ACTIVE = 'active';
  const KEYWORD_DROP = 'dropped';
  const KEYWORD_DISQUALIFY = 'disqualified';
  const KEYWORD_REGISTERED = 'registered';
  const activePlayers = tournamentPlayers.filter(p => p.status === KEYWORD_ACTIVE || p.status === KEYWORD_REGISTERED);
  const droppedPlayers = tournamentPlayers.filter(p => p.status === KEYWORD_DROP);
  const disqualifiedPlayers = tournamentPlayers.filter(p => p.status === KEYWORD_DISQUALIFY)
  
  console.log(tournamentPlayers.map(p => p.status)); // See what actual values you're comparing
  console.log('CsvParserModule:', CsvParserModule);

  const tournamentId = 'e29fbe3f-1755-43cc-a27a-393ec6d80a09';

  const [editingDecklist, setEditingDecklist] = useState({});
  const [decklistDrafts, setDecklistDrafts] = useState({});
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [savingDecklist, setSavingDecklist] = useState({});
  const [droppingPlayer, setDroppingPlayer] = useState({});
  const [disqualifyingPlayer, setDisqualifiedPlayers] = useState({});

  useEffect(() => {
    async function loadPlayers() {
      try {
        const data = await fetchTournamentPlayers(tournamentId);
        setTournamentPlayers(data);
      } catch (err) {
        console.error('Error fetching players:', err);
        setError(err.message);
      } finally {
        setLoading(false);
      }
    }
    loadPlayers();
  }, [tournamentId]);

  const handleStatusChange = async (playerId, status) => {
    setDroppingPlayer(prev => ({ ...prev, [playerId]: true }));
    try {
      await updatePlayerStatus(tournamentId, playerId, status);
      setTournamentPlayers(prev =>
        prev.map(tp =>
          tp.player.playerId === playerId ? { ...tp, status } : tp
        )
      );
      setEditingDecklist(prev => {
        const updated = { ...prev };
        delete updated[playerId];
        return updated;
      });
      setDecklistDrafts(prev => {
        const updated = { ...prev };
        delete updated[playerId];
        return updated;
      });
    } catch (e) {
      alert('Error setting player status.');
      console.error(e);
    } finally {
      setDroppingPlayer(prev => {
        const updated = { ...prev };
        delete updated[playerId];
        return updated;
      });
      setDisqualifiedPlayers(prev => {
        const updated = { ...prev };
        delete updated[playerId];
        return updated;
      });
    }
  };


  const handleCheckInToggle = (playerId, currentStatus) => {
    console.log('sent status is:,', currentStatus)
    if (currentStatus === KEYWORD_REGISTERED) {
      handleStatusChange(playerId, KEYWORD_ACTIVE);
    } else if (currentStatus === KEYWORD_ACTIVE) {
      handleStatusChange(playerId, KEYWORD_REGISTERED);
    }
  };

  const startEditingDecklist = (playerId) => {
    const current = decklistDrafts[playerId] ?? '';
    setEditingDecklist(prev => ({ ...prev, [playerId]: true }));
    setDecklistDrafts(prev => ({ ...prev, [playerId]: current }));
  };

  const saveDecklist = async (playerId) => {
    /*setSavingDecklist(prev => ({ ...prev, [playerId]: true }));
    try {
      await postDecklist(tournamentId, playerId, decklistDrafts[playerId]);
      setEditingDecklist(prev => ({ ...prev, [playerId]: false }));
      alert('Decklist saved.');
    } catch (e) {
      alert('Failed to save decklist.');
      console.error(e);
    } finally {
      setSavingDecklist(prev => ({ ...prev, [playerId]: false }));
    }*/
  };


  const cancelEditing = (playerId) => {
    setEditingDecklist(prev => ({ ...prev, [playerId]: false }));
  };

  const handleDecklistChange = (playerId, value) => {
    setDecklistDrafts(prev => ({ ...prev, [playerId]: value }));
  };

  const lockPlayerToTable = (playerId) => {
    const table = prompt('Enter the table to lock this player to:');
    if (table !== null && table.trim() !== '') {
      alert(`Player ${playerId} locked to table: ${table}`);
      // TODO: Save locked table info as needed
    } else {
      alert('Table lock cancelled or invalid input.');
    }
  };

  const handleReadCsvClick = () => {
    // Create a file input dynamically
    const fileInput = document.createElement('input');
    fileInput.type = 'file';
    fileInput.accept = '.csv';

    fileInput.onchange = async (event) => {
      const file = event.target.files[0];
      if (!file) return;

      const reader = new FileReader();
      reader.onload = async (e) => {
        const csvString = e.target.result;

        try {
          const playerDtos = CsvParserModule.parseCsvToPlayerDtos(csvString);
          await importPlayers(tournamentId, playerDtos);
          alert('Players imported successfully!');
        } catch (err) {
          console.error(err);
          alert('Failed to import players.');
        }
      };

      reader.readAsText(file);
    };

    fileInput.click();
  };
  
  return (
  <div style={{ padding: '0 10px' }}>
    <h2>Player Manager</h2>
    <p>This section allows you to manage players in tournaments.</p>
  {loading && <p>Loading players...</p>}
  {error && <p style={{ color: 'red' }}>Error: {error}</p>}

  {!loading && !error && (
    <>
      {/* Top bar: CSV button left, stats right */}
      <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: '1rem' }}>
        <button onClick={handleReadCsvClick} className='nav-btn'>
          Read Player CSV
        </button>

        <div style={{ display: 'flex', gap: '1rem' }}>
          <div style={{ padding: '0.3rem 0.6rem', backgroundColor: '#ff7f11', color: 'white', borderRadius: '5px', fontWeight: 'bold' }}>
            Active Players: {activePlayers.length}
          </div>
          <div style={{ padding: '0.3rem 0.6rem', backgroundColor: '#999', color: 'white', borderRadius: '5px', fontWeight: 'bold' }}>
            Dropped Players: {droppedPlayers.length}
          </div>
          <div style={{ padding: '0.3rem 0.6rem', backgroundColor: '#999', color: 'white', borderRadius: '5px', fontWeight: 'bold' }}>
            Disqualified Players: {disqualifiedPlayers.length}
          </div>
        </div>
      </div>

      {/* Tables */}
      <ActivePlayersTable
        activePlayers={activePlayers}
        editingDecklist={editingDecklist}
        decklistDrafts={decklistDrafts}
        savingDecklist={savingDecklist}
        droppingPlayer={droppingPlayer}
        startEditingDecklist={startEditingDecklist}
        handleDecklistChange={handleDecklistChange}
        saveDecklist={saveDecklist}
        cancelEditing={cancelEditing}
        lockPlayerToTable={lockPlayerToTable}
        handlePlayerStatus={handleStatusChange}
        keywordDrop={KEYWORD_DROP}
        keywordDisqualify={KEYWORD_DISQUALIFY}
        handleCheckInToggle={handleCheckInToggle}
      />
      <DroppedPlayersTable droppedPlayers={droppedPlayers} />
      <DisqualifiedPlayersTable disqulifiedPlayers={disqualifiedPlayers} />
    </>
  )}
</div>
  );
}

export default PlayerManager;