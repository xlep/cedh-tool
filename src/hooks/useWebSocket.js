import { useEffect, useRef } from 'react';

export function useWebSocket(url, onMessage) {
  const ws = useRef(null);

  useEffect(() => {
    ws.current = new WebSocket(url);
    ws.current.onmessage = (event) => {
      const message = JSON.parse(event.data);
      onMessage(message);
    };
    return () => ws.current && ws.current.close();
  }, [url, onMessage]);

  return ws.current;
}
