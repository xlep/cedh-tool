import React, {useEffect, useRef, useState} from 'react';
import './App.css';
import {useResults} from './ResultsContext'; // Adjust path if needed

function TournamentManager() {
  const [podsData, setPodsData] = useState([]);
  const [loadingRound, setLoadingRound] = useState(false);
  const [rounds, setRounds] = useState([]);
  const [selectedRoundIndex, setSelectedRoundIndex] = useState(0);
  const [searchTerm, setSearchTerm] = useState('');
  const [scoresCalculated, setScoresCalculated] = useState(false);
  const {
    tentativeWinners,
    setTentativeWinners,
    confirmedWinners,
    setConfirmedWinners
  } = useResults();

  const ws = useRef(null);
  const MAX_ROUNDS = 5;
  const tournamentId = "e29fbe3f-1755-43cc-a27a-393ec6d80a09";

  useEffect(() => {
    ws.current = new WebSocket('ws://localhost:8080');
    ws.current.onmessage = (event) => {
      const message = JSON.parse(event.data);
      if (message.type === 'pods') {
        setPodsData(message.data);
      }
    };
    return () => ws.current && ws.current.close();
  }, []);

  useEffect(() => {
    async function fetchRounds() {
      try {
        const res = await fetch(
            `http://localhost:8080/api/v1/tournaments/${tournamentId}/rounds`);
        if (!res.ok) {
          throw new Error('Failed to fetch rounds');
        }
        const data = await res.json();
        setRounds(data);
      } catch (err) {
        console.error(err);
      }
    }

    fetchRounds();
  }, [tournamentId]);

  const handleUndoDraw = (podId) => {
    setConfirmedWinners(prev => {
      const copy = {...prev};
      delete copy[podId]; // Remove the draw (null) confirmation
      return copy;
    });
  };

  const handleNewRound = async () => {
    if (rounds.length >= MAX_ROUNDS) {
      alert(`Maximum number of rounds (${MAX_ROUNDS}) reached.`);
      return;
    }
    setLoadingRound(true);
    try {
      const res = await fetch(
          `http://localhost:8080/api/v1/tournaments/${tournamentId}/rounds`, {
            method: 'POST',
            headers: {'Accept': '*/*'},
          });
      if (!res.ok) {
        throw new Error('Failed to generate new round');
      }
      alert('New round generated successfully');
      const newRounds = await fetch(
          `http://localhost:8080/api/v1/tournaments/${tournamentId}/rounds`).then(
          res => res.json());
      setRounds(newRounds);
      setSelectedRoundIndex(newRounds.length - 1);

      setTentativeWinners({});
      setConfirmedWinners({});
    } catch (err) {
      console.error(err);
      alert('Error generating new round');
    } finally {
      setLoadingRound(false);
    }
  };

  const handleTopCut = () => {
    if (rounds.length !== MAX_ROUNDS) {
      alert('Top Cut is only available after 5 rounds.');
      return;
    }
    alert('Cut To Top 10 button clicked');
    // TODO: Implement top cut logic
  };

  const handleCalculateScores = () => {
    if (rounds.length !== MAX_ROUNDS) {
      alert('Calculate Player Scores is only available after 5 rounds.');
      return;
    }
    alert('Calculate Player Scores button clicked');
    setScoresCalculated(true);
  };

  const handleSelectWinner = (podId, playerId) => {
    if (confirmedWinners[podId]) {
      return;
    }
    setTentativeWinners(prev => {
      if (prev[podId] === playerId) {
        const copy = {...prev};
        delete copy[podId];
        return copy;
      }
      return {...prev, [podId]: playerId};
    });
  };

  const handleDrawPod = async (podId) => {
    const round = rounds[selectedRoundIndex];
    if (!round) {
      return;
    }

    const podType =
        round.round === 6
            ? 'SEMIFINAL'
            : round.round === 7
                ? 'FINAL'
                : 'SWISS';

    const payload = {
      podId,
      tournamentId,
      podType,
      result: 'draw',
      playerId: null,
    };

    try {
      const res = await fetch('http://localhost:8080/api/v1/report/result', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Accept': '*/*',
        },
        body: JSON.stringify(payload),
      });

      if (!res.ok) {
        throw new Error('Failed to submit draw');
      }

      setConfirmedWinners(prev => ({
        ...prev,
        [podId]: null,
      }));

      alert('Pod marked as draw successfully!');
    } catch (e) {
      console.error(e);
      alert('Error submitting draw result.');
    }
  };

  const submitResults = async () => {
    const round = rounds[selectedRoundIndex];
    if (!round) {
      return;
    }

    const pods = round.pods || [];
    const allHaveWinner = pods.every(pod => tentativeWinners[pod.podId]);
    if (!allHaveWinner) {
      alert('Please select a winner for every pod before submitting.');
      return;
    }

    const payload = pods.map(pod => {
      const podType =
          round.round === 6
              ? 'SEMIFINAL'
              : round.round === 7
                  ? 'FINAL'
                  : 'SWISS';

      return {
        podId: pod.podId,
        tournamentId,
        podType,
        result: 'win',
        playerId: tentativeWinners[pod.podId],
      };
    });

    try {
      const res = await fetch(
          'http://localhost:8080/api/v1/report/result/batch', {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json',
              'Accept': '*/*',
            },
            body: JSON.stringify({results: payload}),
          });
      if (!res.ok) {
        throw new Error('Failed to submit winners');
      }

      setConfirmedWinners(prev => ({
        ...prev,
        ...tentativeWinners,
      }));

      setTentativeWinners({});
      alert('All winners submitted successfully!');
    } catch (e) {
      console.error(e);
      alert('Error submitting winners.');
    }
  };

  const filteredPods =
      rounds[selectedRoundIndex]?.pods?.filter((pod) =>
          pod.seats.some((seat) => {
            const fullName = `${seat.firstname} ${seat.lastname}`.toLowerCase();
            return fullName.includes(searchTerm.toLowerCase());
          })
      ) || [];

  const canSubmit = (() => {
    const round = rounds[selectedRoundIndex];
    if (!round) {
      return false;
    }
    const pods = round.pods || [];
    if (pods.length === 0) {
      return false;
    }
    return pods.every(pod => tentativeWinners[pod.podId]) && Object.keys(
        confirmedWinners).length === 0;
  })();

  return (
      <div style={{padding: '0 10px'}}>
        <h2>Tournament Manager</h2>
        <p>This section allows you to manage rounds, top cut, and other
          features.</p>

        <div style={{marginTop: '1rem', marginBottom: '1rem'}}>
          <button
              onClick={handleNewRound}
              disabled={loadingRound || rounds.length >= MAX_ROUNDS}
              style={{marginRight: '10px'}}
          >
            {loadingRound ? 'Generating...' : 'Generate New Round'}
          </button>

          <button
              onClick={handleCalculateScores}
              disabled={rounds.length !== MAX_ROUNDS || scoresCalculated}
              style={{marginRight: '10px'}}
          >
            Calculate Player Scores
          </button>

          <button
              onClick={handleTopCut}
              disabled={rounds.length !== MAX_ROUNDS}
          >
            Cut to Top 10
          </button>
        </div>

        <div className="nav-buttons" style={{
          marginBottom: '1rem',
          display: 'flex',
          alignItems: 'center',
          justifyContent: 'space-between',
          flexWrap: 'wrap'
        }}>
          <div>
            {rounds.map((round, index) => (
                <button
                    key={round.round}
                    onClick={() => {
                      setSelectedRoundIndex(index);
                      setTentativeWinners({});
                      setConfirmedWinners({});
                    }}
                    className={`nav-btn ${index === selectedRoundIndex
                        ? 'active' : ''}`}
                    type="button"
                    style={{marginRight: '5px', marginBottom: '5px'}}
                >
                  Round {round.round}
                </button>
            ))}
          </div>

          <button
              className="nav-btn submit-btn"
              disabled={!canSubmit}
              onClick={submitResults}
              type="button"
              style={{marginBottom: '5px'}}
          >
            Submit Results
          </button>
        </div>

        <div className="search-bar-container">
          <input
              type="text"
              placeholder="Search for a player..."
              value={searchTerm}
              onChange={(e) => setSearchTerm(e.target.value)}
              className="search-bar"
          />
        </div>

        <div style={{marginBottom: '1rem'}}>
          <h3>All Rounds</h3>
          {rounds.length > 0 ? (
              <>
                <div className="pods-grid">
                  {(searchTerm ? filteredPods
                      : rounds[selectedRoundIndex].pods).map((pod) => {
                    const hasWinner = tentativeWinners[pod.podId]
                        || confirmedWinners[pod.podId];

                    return (
                        <div key={pod.podId} className="pod">
                          <h2>Pod {pod.podName}</h2>
                          <ul>
                            {pod.seats
                            .slice()
                            .sort((a, b) => a.seat - b.seat)
                            .map((seat) => {
                              const isTentativeWinner = tentativeWinners[pod.podId]
                                  === seat.playerId;
                              const isConfirmedWinner = confirmedWinners[pod.podId]
                                  === seat.playerId;
                              const isDrawnPod = confirmedWinners[pod.podId]
                                  === null;

                              return (
                                  <li
                                      key={seat.playerId}
                                      className={`
          ${isTentativeWinner ? 'tentative-winner' : ''}
          ${isConfirmedWinner ? 'confirmed-winner' : ''}
          ${isDrawnPod ? 'drawn-pod' : ''}
        `}
                                      style={{
                                        cursor: confirmedWinners[pod.podId]
                                        !== undefined ? 'default' : 'pointer',
                                        fontWeight: isConfirmedWinner ? 'bold'
                                            : 'normal',
                                        textDecoration: isConfirmedWinner
                                            ? 'underline' : 'none',
                                      }}
                                      onClick={() => handleSelectWinner(
                                          pod.podId, seat.playerId)}
                                      title={
                                        confirmedWinners[pod.podId]
                                        !== undefined
                                            ? 'Result confirmed — selection locked'
                                            : 'Click to select winner'
                                      }
                                  >
                                    {seat.firstname} {seat.lastname} (Seat {seat.seat})
                                    {isTentativeWinner && !isConfirmedWinner
                                        ? ' (Winner)' : ''}
                                    {isConfirmedWinner ? ' (Confirmed Winner)'
                                        : ''}
                                    {isDrawnPod ? ' (Draw)' : ''}
                                  </li>
                              );
                            })}

                          </ul>

                          {!hasWinner && (
                              <button
                                  onClick={() => handleDrawPod(pod.podId)}
                                  className="draw-btn"
                                  style={{marginTop: '10px'}}
                              >
                                Draw Pod
                              </button>
                          )}
                        </div>
                    );
                  })}
                </div>
              </>
          ) : (
              <p>No rounds yet. Click "Generate New Round" to create one.</p>
          )}
        </div>
      </div>
  );
}

export default TournamentManager;
