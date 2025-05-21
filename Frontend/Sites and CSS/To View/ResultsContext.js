// ResultsContext.js
import React, { createContext, useContext, useState } from 'react';

const ResultsContext = createContext();

export function ResultsProvider({ children }) {
  const [confirmedWinners, setConfirmedWinners] = useState({});
  const [tentativeWinners, setTentativeWinners] = useState({});

  return (
      <ResultsContext.Provider value={{ confirmedWinners, setConfirmedWinners, tentativeWinners, setTentativeWinners }}>
        {children}
      </ResultsContext.Provider>
  );
}

export function useResults() {
  return useContext(ResultsContext);
}
