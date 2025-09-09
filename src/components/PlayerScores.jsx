// src/components/PlayerScores.js
import React, { useEffect, useState } from 'react';
import { fetchTournamentPlayers } from '../api/TournamentPlayerApi';
import { fetchTournament } from '../api/TournamentApi';

const TOURNAMENT_ID = 'e29fbe3f-1755-43cc-a27a-393ec6d80a09';

export default function PlayerScores() {
  const [players, setPlayers] = useState([]);
  const [tournamentName, setTournamentName] = useState('');
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    async function loadPlayersAndTournament() {
      try {
        const [allPlayers, tournament] = await Promise.all([
          fetchTournamentPlayers(TOURNAMENT_ID),
          fetchTournament(TOURNAMENT_ID)
        ]);
        const sorted = [...allPlayers].sort((a, b) => parseFloat(b.score) - parseFloat(a.score));
        setPlayers(sorted);
        setTournamentName(tournament.name || '');
      } catch (err) {
        console.error(err);
        setError('Failed to fetch tournament data.');
      } finally {
        setLoading(false);
      }
    }

    loadPlayersAndTournament();
  }, []);

  if (loading) return <p>Loading players...</p>;
  if (error) return <p>{error}</p>;

  return (
    <div className="container">
      <h2 className="section-title">Player scores for {tournamentName}</h2>
      {players.length === 0 ? (
        <p>No players available.</p>
      ) : (
        <table className="players-table">
          <thead>
            <tr>
              <th>Rank</th>
              <th>Player Name</th>
              <th>Score</th>
            </tr>
          </thead>
          <tbody>
            {players.map((tournamentPlayer, index) => {
              const isTop2 = index === 1;
              const isTop10 = index === 9;
              const borderStyle = isTop2
                ? { borderBottom: '4px solid #4caf50' }
                : isTop10
                ? { borderBottom: '4px solid #ffcc00' }
                : {};

              return (
                <tr
                  key={tournamentPlayer.player?.playerId || index}
                  style={borderStyle}
                >
                  <td>{index + 1}</td>
                  <td>
                    {`${tournamentPlayer.player?.firstname || ''} ${tournamentPlayer.player?.lastname || ''}`.trim() ||
                      'Unnamed Player'}
                  </td>
                  <td>{tournamentPlayer.score}</td>
                </tr>
              );
            })}
          </tbody>
        </table>
      )}
    </div>
  );
}
