// components/DroppedPlayersTable.jsx
import React from 'react';

function DroppedPlayersTable({ droppedPlayers }) {
  return (
    <div>
      <h2>Dropped Players</h2>
      {droppedPlayers.length === 0 ? (
        <p>No dropped players found.</p>
      ) : (
        <table className="dropped-players-table">
          <thead>
            <tr>
              <th>Player</th>
              <th>Status</th>
            </tr>
          </thead>
          <tbody>
            {droppedPlayers.map(tournamentPlayer => {
              const playerId = tournamentPlayer.player.playerId;
              return (
                <tr key={playerId}>
                  <td>{tournamentPlayer.player.firstname} {tournamentPlayer.player.lastname}</td>
                  <td>
                    <span className="tag dropped">Dropped</span>
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

export default DroppedPlayersTable;
