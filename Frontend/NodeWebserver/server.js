const WebSocket = require('ws');

const wss = new WebSocket.Server({ port: 8080 }, () => {
  console.log("WebSocket server running on ws://localhost:8080");
});

wss.on('connection', function connection(ws) {
  console.log("Client connected");

  ws.on('message', function incoming(message) {
    console.log('Received:', message);

    // Echo the message back to the client (optional)
    // ws.send(message);

    const parsed = JSON.parse(message);

    // Broadcast to all clients
    wss.clients.forEach((client) => {
      if (client.readyState === WebSocket.OPEN) {
        client.send(JSON.stringify(parsed));
      }
    });
  });

  ws.on('close', () => {
    console.log("Client disconnected");
  });
});
