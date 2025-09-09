export function getPodType(roundNumber) {
  if (roundNumber === 6) return 'SEMIFINAL';
  if (roundNumber === 7) return 'FINAL';
  return 'SWISS';
}

export function filterPodsBySearchTerm(pods = [], searchTerm = '') {
  if (!searchTerm) return pods;
  const lowerTerm = searchTerm.toLowerCase();
  return pods.filter(pod =>
    pod.seats.some(seat => {
      const fullName = `${seat.firstname} ${seat.lastname}`.toLowerCase();
      return fullName.includes(lowerTerm);
    })
  );
}
