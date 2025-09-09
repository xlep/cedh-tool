// components/ActivePlayersTable.jsx
import React from 'react';

function ActivePlayersTable({
  activePlayers,
  editingDecklist,
  decklistDrafts,
  savingDecklist,
  droppingPlayer,
  startEditingDecklist,
  handleDecklistChange,
  saveDecklist,
  cancelEditing,
  lockPlayerToTable,
  handlePlayerStatus,
  keywordDrop,
  keywordDisqualify,
  handleCheckInToggle
}) {
  return (
    <div>
      <h2>Active Players</h2>
      {activePlayers.length === 0 ? (
        <p>No active players found.</p>
      ) : (
        <table className="players-table" style={{ tableLayout: 'fixed' }}>
          <thead>
            <tr>
              <th style={{ width: '10%' }}>Player</th>
              <th style={{ width: '22.5%' }}>Email</th>
              <th style={{ width: '22.5%' }}>Decklist</th>
              <th style={{ width: '7%' }}>Status</th>
              <th style={{ width: '35%' }}>Actions</th>
            </tr>
          </thead>
          <tbody>
            {activePlayers.map(tournamentPlayer => {
              const playerId = tournamentPlayer.player.playerId;
              const isEditing = editingDecklist[playerId];
              const decklist = decklistDrafts[playerId] ?? '';
              const isSaving = savingDecklist[playerId];
              const isDropping = droppingPlayer[playerId];

              return (
                <tr key={playerId}>
                  <td>{tournamentPlayer.player.firstname} {tournamentPlayer.player.lastname}</td>
                  <td>{tournamentPlayer.player.email || '—'}</td>
                  <td>
                    {isEditing ? (
                      <>
                        <input
                          type="text"
                          value={decklist}
                          onChange={e => handleDecklistChange(playerId, e.target.value)}
                          style={{ width: '100%' }}
                          disabled={isSaving}
                        />
                        <div style={{ marginTop: '4px' }}>
                          <button onClick={() => saveDecklist(playerId)} disabled={isSaving}>
                            {isSaving ? 'Saving...' : 'Save'}
                          </button>
                          <button onClick={() => cancelEditing(playerId)} style={{ marginLeft: '4px' }} disabled={isSaving}>
                            Cancel
                          </button>
                        </div>
                      </>
                    ) : (
                      decklist || '—'
                    )}
                  </td>
                  <td>
                    <span className={`tag ${tournamentPlayer.status === 'active' ? 'checked-in' : 'sign-up'}`}>
                      {tournamentPlayer.status === 'active' ? 'Active' : 'Registered'}
                    </span>
                  </td>
                  <td>
                    <button
                      onClick={() =>
                        handleCheckInToggle(playerId, tournamentPlayer.status)}
                      disabled={droppingPlayer[playerId]}
                    >
                      {tournamentPlayer.status === 'active' ? 'Uncheck' : 'Check In'}
                    </button>
                    <button
                      onClick={() => startEditingDecklist(playerId)}
                      style={{ marginLeft: '8px' }}
                      disabled={isDropping || isEditing}
                    >
                      Modify Decklist
                    </button>
                    <button
                      onClick={() => lockPlayerToTable(playerId)}
                      style={{ marginLeft: '8px' }}
                      disabled={isDropping}
                    >
                      Lock Player to Table
                    </button>
                    <button
                      onClick={() => handlePlayerStatus(playerId, keywordDrop)}
                      style={{ marginLeft: '8px', color: 'red' }}
                      disabled={isDropping}
                    >
                      {isDropping ? 'Dropping...' : 'Drop'}
                    </button>
                    <button
                      onClick={() => handlePlayerStatus(playerId, keywordDisqualify)}
                      style={{ marginLeft: '8px', color: 'red' }}>
                      Disqualify
                    </button>
                  </td>
                </tr>
              );
            })}
          </tbody>
        </table>
      )}
    </div>
  );
}

export default ActivePlayersTable;
