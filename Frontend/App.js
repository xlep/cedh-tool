import React, { useState, useEffect, useRef } from "react";
import '../../../react-app/my-app/src/style.css';

export default function TournamentPods() {
  const [searchTerm, setSearchTerm] = useState("");
  const [podsData, setPodsData] = useState([]);
  const [timeLeft, setTimeLeft] = useState(() => {
    const saved = localStorage.getItem("timeLeft");
    return saved ? parseInt(saved, 10) : 0;
  });
  const [overtime, setOvertime] = useState(() => {
    const saved = localStorage.getItem("overtime");
    return saved ? JSON.parse(saved) : false;
  });
  const [timerRunning, setTimerRunning] = useState(() => {
    const saved = localStorage.getItem("timerRunning");
    return saved ? JSON.parse(saved) : false;
  });

  const ws = useRef(null);

  useEffect(() => {
    ws.current = new WebSocket("ws://localhost:8080");

    ws.current.onmessage = (event) => {
      const message = JSON.parse(event.data);
      if (message.type === "pods") {
        setPodsData(message.data);
      }
      if (message.type === "start-timer") {
        if (!timerRunning) {
          setTimerRunning(true);
          setTimeLeft(90 * 60);
          setOvertime(false);
        }
      }
    };

    return () => {
      if (ws.current) ws.current.close();
    };
  }, [timerRunning]);

  useEffect(() => {
    localStorage.setItem("timeLeft", timeLeft);
    localStorage.setItem("overtime", JSON.stringify(overtime));
    localStorage.setItem("timerRunning", JSON.stringify(timerRunning));
  }, [timeLeft, overtime, timerRunning]);

  useEffect(() => {
    if (!timerRunning) return;

    const interval = setInterval(() => {
      setTimeLeft((prev) => {
        if (prev > 0) {
          return prev - 1;
        } else if (!overtime) {
          setOvertime(true);
          return 15 * 60;
        } else {
          return 0;
        }
      });
    }, 1000);

    return () => clearInterval(interval);
  }, [overtime, timerRunning]);

  const formatTime = (seconds) => {
    const m = Math.floor(seconds / 60).toString().padStart(2, '0');
    const s = (seconds % 60).toString().padStart(2, '0');
    return `${m}:${s}`;
  };

  const filteredPods = podsData.filter((pod) =>
    pod.players.some((player) =>
      player.toLowerCase().includes(searchTerm.toLowerCase())
    )
  );

  return (
    <div className="container">
      <div className="header">
        <h1 className="title">Tournament Pods</h1>
        {timerRunning && (
          <div className="timer-container">
            <span className={`timer-label ${overtime ? 'overtime' : ''}`}>
              {overtime ? 'Overtime' : 'Round Time'}
            </span>
            <div className={`timer ${overtime ? 'overtime' : ''}`}>
              {timeLeft > 0 ? formatTime(timeLeft) : "Timeout"}
            </div>
          </div>
        )}
      </div>

      <input
        type="text"
        placeholder="Search for a player..."
        value={searchTerm}
        onChange={(e) => setSearchTerm(e.target.value)}
        className="search-bar"
      />

      <div className="pods-grid">
        {filteredPods.map((pod) => (
          <div key={pod.id} className="pod">
            <h2>Pod {pod.id}</h2>
            <ul>
              {pod.players.map((player, index) => (
                <li key={index}>{player}</li>
              ))}
            </ul>
          </div>
        ))}
        {filteredPods.length === 0 && (
          <p className="no-results">No players found.</p>
        )}
      </div>
    </div>
  );
}
