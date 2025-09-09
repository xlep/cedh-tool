const API_BASE = process.env.REACT_APP_API_BASE_URL;

export async function fetchRounds(tournamentId) {
  const res = await fetch(`${API_BASE}/tournaments/${tournamentId}/rounds`);
  if (!res.ok) throw new Error('Failed to fetch rounds');
  return res.json();
}

export async function fetchLatestRound(tournamentId) {
  const res = await fetch(`${API_BASE}/tournaments/${tournamentId}/latest-round`);
  if (!res.ok) throw new Error('Failed to fetch rounds');
  return res.json();
}

export async function fetchTournament(tournamentId) {
  const res = await fetch(`${API_BASE}/tournaments/${tournamentId}`);
  if (!res.ok) throw new Error('Failed to fetch rounds');
  return res.json();
}

export async function generateNewRound(tournamentId) {
  const res = await fetch(`${API_BASE}/tournaments/${tournamentId}/rounds`, {
    method: 'POST',
    headers: { Accept: '*/*' },
  });
  if (!res.ok) throw new Error('Failed to generate new round');
}

export async function submitResult(podId, payload) {
  const res = await fetch(`${API_BASE}/pods/${podId}/results`, {
    method: 'PUT',
    headers: { 'Content-Type': 'application/json', Accept: '*/*' },
    body: JSON.stringify(payload),
  });
  if (!res.ok) throw new Error('Failed to submit result');
}

export async function importPlayers(tournamentId, playersDto) {
  const res = await fetch(`${API_BASE}/tournaments/${tournamentId}/players`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      Accept: '*/*',
    },
    body: JSON.stringify(playersDto),
  });

  if (!res.ok) {
    throw new Error('Failed to import players');
  }
}

