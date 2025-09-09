import React, { useState, useEffect, useCallback } from 'react';
import {
  fetchRounds,
  generateNewRound,
  submitResult
} from './api/TournamentApi';
import { fetchTopCut } from './api/ScoreApi';
import { useWebSocket } from './hooks/useWebSocket';
import { getPodType, filterPodsBySearchTerm } from './utils/podUtils';
import Pod from './components/Pod';

const MAX_ROUNDS = 5;
const TOURNAMENT_ID = 'e29fbe3f-1755-43cc-a27a-393ec6d80a09';
const TOP_CUT = 10;

function TournamentManager() {
  const [podsData, setPodsData] = useState([]);
  const [rounds, setRounds] = useState([]);
  const [loadingRound, setLoadingRound] = useState(false);
  const [selectedRoundIndex, setSelectedRoundIndex] = useState(0);
  const [searchTerm, setSearchTerm] = useState('');
  const [submittedResults, setSubmittedResults] = useState({});
  const [tentativeWinnersByRound, setTentativeWinnersByRound] = useState({});
  const [confirmedWinnersByRound, setConfirmedWinnersByRound] = useState({});

  // WebSocket handler
  const onMessage = useCallback((message) => {
    if (message.type === 'pods') {
      setPodsData(message.data);
    }
  }, []);
  useWebSocket('ws://localhost:8080', onMessage);

  // Fetch rounds initially and on tournament ID change
  useEffect(() => {
  async function loadRounds() {
    try {
      const data = await fetchRounds(TOURNAMENT_ID);
      setRounds(data);

      // Parse confirmed winners and submitted results from data
      const confirmedByRound = {};
      const submittedByRound = {};

      data.forEach((round, roundIndex) => {
        const confirmedWinners = {};
        round.pods.forEach((pod) => {
          // For each pod, find the winner or draw
          // The pod seats have player results: 'win', 'loss', or 'draw'
          // 'win' means the player is the winner, 'draw' means pod draw

          // Check if the pod is a draw: if any seat has result 'draw'
          const isDraw = pod.seats.some(seat => seat.result === 'draw');

          if (isDraw) {
            // Mark pod winner as null for draw
            confirmedWinners[pod.podId] = null;
            submittedByRound[pod.podId] = true;
          } else {
            // Otherwise find the winner by 'win' result
            const winnerSeat = pod.seats.find(seat => seat.result === 'win');
            if (winnerSeat) {
              confirmedWinners[pod.podId] = winnerSeat.playerId;
              submittedByRound[pod.podId] = true;
            }
          }
          // If no result found, do nothing (no confirmed winner)
        });
        confirmedByRound[roundIndex] = confirmedWinners;
      });

      setConfirmedWinnersByRound(confirmedByRound);
      setSubmittedResults(submittedByRound);
      setTentativeWinnersByRound({}); // clear tentative
    } catch (err) {
      console.error(err);
    }
  }
  loadRounds();
}, []);


  const tentativeWinners = tentativeWinnersByRound[selectedRoundIndex] || {};
  const confirmedWinners = confirmedWinnersByRound[selectedRoundIndex] || {};
  const currentRound = rounds[selectedRoundIndex];

  const handleSelectWinner = (podId, playerId) => {
    if (confirmedWinners[podId]) return;
    setTentativeWinnersByRound(prev => {
      const roundWinners = prev[selectedRoundIndex] || {};
      const updated = roundWinners[podId] === playerId
        ? (() => { const copy = { ...roundWinners }; delete copy[podId]; return copy; })()
        : { ...roundWinners, [podId]: playerId };
      return { ...prev, [selectedRoundIndex]: updated };
    });
  };

  const handleDrawPod = (podId) => {
    setConfirmedWinnersByRound(prev => {
      const roundConfirmed = prev[selectedRoundIndex] || {};
      return { ...prev, [selectedRoundIndex]: { ...roundConfirmed, [podId]: null } };
    });
    setTentativeWinnersByRound(prev => {
      const roundTentative = prev[selectedRoundIndex] || {};
      const copy = { ...roundTentative };
      delete copy[podId];
      return { ...prev, [selectedRoundIndex]: copy };
    });
  };

  const handleUndoDraw = (podId) => {
    setConfirmedWinnersByRound(prev => {
      const roundConfirmed = prev[selectedRoundIndex] || {};
      const copy = { ...roundConfirmed };
      delete copy[podId];
      return { ...prev, [selectedRoundIndex]: copy };
    });
  };

  const handleUndoResult = (podId) => {
    setConfirmedWinnersByRound(prev => {
      const roundConfirmed = prev[selectedRoundIndex] || {};
      const copy = { ...roundConfirmed };
      delete copy[podId];
      return { ...prev, [selectedRoundIndex]: copy };
    });
    setSubmittedResults(prev => {
      const copy = { ...prev };
      delete copy[podId];
      return copy;
    });
  };

  const submitSingleResult = async (pod) => {
    if (!currentRound) return;

    const podType = getPodType(currentRound.round);
    const winnerId = confirmedWinners[pod.podId] !== undefined
      ? confirmedWinners[pod.podId]
      : tentativeWinners[pod.podId];

    if (winnerId === undefined) {
      return;
    }

    const payload = {
      podId: pod.podId,
      tournamentId: TOURNAMENT_ID,
      podType,
      result: winnerId === null ? 'draw' : 'win',
      playerId: winnerId,
    };

    try {
      await submitResult(pod.podId, payload);

      setConfirmedWinnersByRound(prev => {
        const roundConfirmed = prev[selectedRoundIndex] || {};
        return { ...prev, [selectedRoundIndex]: { ...roundConfirmed, [pod.podId]: winnerId } };
      });

      setTentativeWinnersByRound(prev => {
        const roundTentative = prev[selectedRoundIndex] || {};
        const copy = { ...roundTentative };
        delete copy[pod.podId];
        return { ...prev, [selectedRoundIndex]: copy };
      });

      setSubmittedResults(prev => ({ ...prev, [pod.podId]: true }));

      // Update rounds after semifinal submit
      if (currentRound.round === 6) {
        const updatedRounds = await fetchRounds(TOURNAMENT_ID);
        setRounds(updatedRounds);
        const finalRoundIndex = updatedRounds.findIndex(r => r.round === 7);
        if (finalRoundIndex !== -1) {
          setSelectedRoundIndex(finalRoundIndex);
        }
      }
    } catch (err) {
      console.error(err);
    }
  };

  const handleNewRound = async () => {
    if (rounds.length >= MAX_ROUNDS) {
      return;
    }
    setLoadingRound(true);
    try {
      await generateNewRound(TOURNAMENT_ID);
      const newRounds = await fetchRounds(TOURNAMENT_ID);
      setRounds(newRounds);
      setSelectedRoundIndex(newRounds.length - 1);
      setTentativeWinnersByRound({});
      setConfirmedWinnersByRound({});
      setSubmittedResults({});
    } catch (err) {
      console.error(err);
    } finally {
      setLoadingRound(false);
    }
  };

  const handleTopCut = async () => {
    try {
      const data = await fetchTopCut(TOURNAMENT_ID, TOP_CUT);
      if (data) {
        console.log('Top cut data:', data);
      } else {
        console.log('Top cut completed, but no response body');
      }
    } catch (err) {
      console.error(err);
    }
  };

  const filteredPods = filterPodsBySearchTerm(currentRound?.pods || [], searchTerm);

  return (
    <div style={{ padding: '0 10px' }}>
      <h2>Tournament Manager</h2>
      <p>This section allows you to manage rounds, top cut, and other features.</p>

      <div style={{ marginTop: '1rem', marginBottom: '1rem' }}>
        <button onClick={handleNewRound} disabled={loadingRound || rounds.length >= MAX_ROUNDS} style={{ marginRight: '10px' }}>
          {loadingRound ? 'Generating...' : 'Generate New Round'}
        </button>
        <button onClick={handleTopCut} disabled={rounds.length !== MAX_ROUNDS}>
          Cut to Top 10
        </button>
      </div>

      <div className="nav-buttons" style={{ marginBottom: '1rem', display: 'flex', alignItems: 'center', justifyContent: 'space-between', flexWrap: 'wrap' }}>
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

      <div className="search-bar-container">
        <input
          type="text"
          placeholder="Search for a player..."
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
          className="search-bar"
        />
      </div>

      <div style={{ marginBottom: '1rem' }}>
        <h3>All Rounds</h3>
        {rounds.length > 0 ? (
          <div className="pods-grid">
            {(searchTerm ? filteredPods : currentRound?.pods || []).map(pod => (
              <Pod
                key={pod.podId}
                pod={pod}
                tentativeWinner={tentativeWinners[pod.podId]}
                confirmedWinner={confirmedWinners[pod.podId]}
                onSelectWinner={handleSelectWinner}
                onSubmitResult={submitSingleResult}
                onDrawPod={handleDrawPod}
                onUndoDraw={handleUndoDraw}
                onUndoResult={handleUndoResult}
                isResultSubmitted={submittedResults[pod.podId]}
              />
            ))}
          </div>
        ) : (
          <p>No rounds available.</p>
        )}
      </div>
    </div>
  );
}

export default TournamentManager;
