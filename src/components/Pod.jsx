import React from 'react';

function Pod({
  pod,
  tentativeWinner,
  confirmedWinner,
  onSelectWinner,
  onSubmitResult,
  onDrawPod,
  onUndoDraw,
  onUndoResult,
  isResultSubmitted,
}) {
  const isDraw = confirmedWinner === null;
  const isByePod = pod.podName === 999;

  return (
    <div className="pod">
      <h2>{isByePod ? 'Bye' : `Pod ü ${pod.podName}`}</h2>
      <ul>
        {pod.seats
          .slice()
          .sort((a, b) => a.seat - b.seat)
          .map((seat) => {
            const isTentativeWinner = tentativeWinner === seat.playerId;
            const isConfirmedWinner = confirmedWinner === seat.playerId;

            const podSelectedClass = isDraw ? 'pod-draw-selected' : '';
            const playerSelectedClass =
              isDraw
                ? 'player-draw-selected'
                : isConfirmedWinner
                ? 'player-confirmed-selected'
                : isTentativeWinner
                ? 'player-tentative-selected'
                : '';

            return (
              <li
                key={seat.playerId}
                onClick={isByePod || confirmedWinner ? undefined : () => onSelectWinner(pod.podId, seat.playerId)}
                className={`${playerSelectedClass} ${podSelectedClass}`}
                style={{ cursor: isByePod || confirmedWinner ? 'default' : 'pointer' }}
                title={`Score: ${seat.score}, Rank: ${seat.rank}, Record: ${seat.wins}-${seat.losses}-${seat.draws}`}
              >
                {seat.firstname} {seat.lastname}
              </li>
            );
          })}
      </ul>

      {!isByePod && (
        <div style={{ marginTop: '10px', display: 'flex', gap: '10px', alignItems: 'center' }}>
          {isResultSubmitted ? (
            <button onClick={() => onUndoResult(pod.podId)} type="button">
              Undo Result
            </button>
          ) : (
            <>
              <button className="submit-btn" onClick={() => onSubmitResult(pod)} type="button">
                Submit Result
              </button>
              {!confirmedWinner && (
                isDraw ? (
                  <button className="undo-draw-btn" onClick={() => onUndoDraw(pod.podId)} type="button">
                    Undo Draw
                  </button>
                ) : (
                  <button className="draw-btn" onClick={() => onDrawPod(pod.podId)} type="button">
                    Draw Pod
                  </button>
                )
              )}
            </>
          )}
        </div>
      )}
    </div>
  );
}

export default Pod;
