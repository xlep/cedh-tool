// components/DisqualifiedPlayersTable.jsx
import React from 'react';

function DisqualifiedPlayersTable({ disqulifiedPlayers }) {
  return (
    <div>
      <h2>Disqualified Players</h2>
      {disqulifiedPlayers.length === 0 ? (
        <p>No disqualified players found.</p>
      ) : (
        <table className="dropped-players-table">
          <thead>
            <tr>
              <th>Player</th>
              <th>Status</th>
            </tr>
          </thead>
          <tbody>
            {disqulifiedPlayers.map(tournamentPlayer => {
              const playerId = tournamentPlayer.player.playerId;
              return (
                <tr key={playerId}>
                  <td>{tournamentPlayer.player.firstname} {tournamentPlayer.player.lastname}</td>
                  <td>
                    <span className="tag dropped">Disqualified</span>
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

export default DisqualifiedPlayersTable;
