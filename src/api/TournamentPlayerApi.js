const API_BASE = process.env.REACT_APP_API_BASE_URL;

export async function fetchTournamentPlayers(tournamentId) {
    const response = await fetch(`${API_BASE}/tournament-players/tournament/${tournamentId}`, {
    method: 'GET'});
    if (!response.ok) throw new Error(`Failed to load players: ${response.statusText}`);
    return await response.json();
}

export async function updatePlayerStatus(tournamentId, playerId, status) {
    const response = await fetch(`${API_BASE}/tournament-players/tournament/${tournamentId}/player/${playerId}/status/${status}`, {
        method: 'PUT',
    });
    if (!response.ok) throw new Error(`Failed to update status for player ${playerId}`);
}
// todo this feature has not been implemented yet and there is no api

export async function postDecklist(tournamentId, playerId, decklist) {
  const response = await fetch(`${API_BASE}/tournament-players/${tournamentId}/decklist/${playerId}`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ decklist }),
  });
  if (!response.ok) throw new Error(`Failed to save decklist for player ${playerId}`);
}
