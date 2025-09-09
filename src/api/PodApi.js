const API_BASE = process.env.REACT_APP_API_BASE_URL;

export async function submitResult(podId, payload) {
  const res = await fetch(`${API_BASE}/pods/${podId}/results`, {
    method: 'PUT',
    headers: { 'Content-Type': 'application/json', Accept: '*/*' },
    body: JSON.stringify(payload),
  });
  if (!res.ok) throw new Error('Failed to submit result');
}