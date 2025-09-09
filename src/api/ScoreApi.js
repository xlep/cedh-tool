const API_BASE = process.env.REACT_APP_API_BASE_URL;

export async function fetchTopCut(tournamentId, topCut) {
  const res = await fetch(`${API_BASE}/tournaments/${tournamentId}/topcut/${topCut}`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
  });
  if (!res.ok) throw new Error('Failed to fetch the top cut');
  const contentLength = res.headers.get('Content-Length');
  if (contentLength && parseInt(contentLength) > 0) {
    return res.json();
  }
  return null;
}