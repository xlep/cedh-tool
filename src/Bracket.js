import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';

// Replace these with your actual API calls
import { fetchRounds } from './api/TournamentApi';

const TOURNAMENT_ID = 'e29fbe3f-1755-43cc-a27a-393ec6d80a09';

function Bracket() {
    const { tournamentId } = useParams();

    const [rounds, setRounds] = useState([]);
    const [selectedRoundIndex, setSelectedRoundIndex] = useState(0);
    
    // Initialize searchTerm from localStorage
    const [searchTerm, setSearchTerm] = useState(() => {
        return localStorage.getItem('bracketSearchTerm') || '';
    });
    
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    // Load all rounds once on mount or when tournamentId changes
    useEffect(() => {
        const loadRounds = async () => {
            setLoading(true);
            try {
                const allRounds = await fetchRounds(TOURNAMENT_ID); // Make sure this fetches ALL rounds, not just the latest
                if (allRounds.length === 0) {
                    setError('No rounds found');
                } else {
                    setRounds(allRounds);
                    setSelectedRoundIndex(0);
                    setError(null);
                }
            } catch (err) {
                setError('Failed to load rounds');
            } finally {
                setLoading(false);
            }
        };

        loadRounds();
    }, [tournamentId]);

    // Save search term to localStorage on every change
    const handleSearchChange = (e) => {
        setSearchTerm(e.target.value);
        localStorage.setItem('bracketSearchTerm', e.target.value);
    };

    if (loading) return <div className="container">Loading rounds...</div>;
    if (error) return <div className="container">{error}</div>;
    if (rounds.length === 0) return <div className="container no-results">No rounds available.</div>;

    const selectedRound = rounds[selectedRoundIndex];

    // Filter pods by search term on player name (case insensitive)
    const filteredPods = selectedRound.pods.filter(pod =>
        pod.seats.some(seat =>
            (`${seat.firstname} ${seat.lastname}`).toLowerCase().includes(searchTerm.toLowerCase())
        )
    );

    return (
        <div className="container">
            {/* Round Navigation Buttons */}
            <div
                className="nav-buttons"
                style={{
                    marginBottom: '1rem',
                    display: 'flex',
                    alignItems: 'center',
                    justifyContent: 'space-between',
                    flexWrap: 'wrap',
                }}
            >
                <div>
                    {rounds.map((round, index) => (
                        <button
                            key={round.round}
                            onClick={() => setSelectedRoundIndex(index)}
                            className={`nav-btn ${index === selectedRoundIndex ? 'active' : ''}`}
                            type="button"
                            style={{ marginRight: '5px', marginBottom: '5px' }}
                        >
                            Round {round.round}
                        </button>
                    ))}
                </div>
            </div>

            {/* Heading */}
            <h1 className="title">Round {selectedRound.round}</h1>

            {/* Search Bar */}
            <input
                type="text"
                placeholder="Search players by name..."
                value={searchTerm}
                onChange={handleSearchChange}
                style={{
                    marginBottom: '1rem',
                    padding: '0.5rem',
                    width: '100%',
                    maxWidth: '400px',
                    fontSize: '1rem',
                    borderRadius: '4px',
                    border: '1px solid #ccc',
                }}
            />

            {/* Pods Display */}
            {filteredPods.length === 0 ? (
                <div className="no-results">No pods match your search.</div>
            ) : (
                <div className="pods-grid" style={{ display: 'grid', gap: '1rem' }}>
                    {filteredPods.map((pod) => (
                        <div className="pod" key={pod.podId} style={{ border: '1px solid #ddd', padding: '1rem', borderRadius: '8px' }}>
                            <h2>{pod.podName === 999 ? 'Bye' : `Table ${pod.podName}`}</h2>
                            <table className="players-table" style={{ width: '100%', borderCollapse: 'collapse' }}>
                                <thead>
                                    <tr>
                                        <th style={{ textAlign: 'left', padding: '0.5rem', borderBottom: '1px solid #ccc' }}>Player</th>
                                        <th style={{ textAlign: 'left', padding: '0.5rem', borderBottom: '1px solid #ccc', width: '50px' }}>Seat</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {pod.seats
                                        .slice()
                                        .sort((a, b) => a.seat - b.seat)
                                        .map((seat) => (
                                            <tr key={seat.playerId} style={{ borderBottom: '1px solid #eee' }}>
                                                <td style={{ padding: '0.5rem' }}>{seat.firstname} {seat.lastname}</td>
                                                <td style={{ padding: '0.5rem' }}>{seat.seat}</td>
                                            </tr>
                                        ))}
                                </tbody>
                            </table>
                        </div>
                    ))}
                </div>
            )}
        </div>
    );
}

export default Bracket;
