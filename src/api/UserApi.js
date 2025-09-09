const API_BASE = process.env.REACT_APP_API_BASE_URL;

export async function loginUser(username, password) {
  const basicAuth = btoa(`${username}:${password}`);

  const response = await fetch(`${API_BASE}/user/login`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': `Basic ${basicAuth}`,
      'Accept': '*/*',
    },
    body: JSON.stringify({ username, password }),
  });

  if (!response.ok) {
    return false;
  }

  const result = await response.json();
  return result === true; // assuming your endpoint returns a boolean true/false
}
