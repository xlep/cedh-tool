// src/utils/csvParser.js

export function parseCsvToPlayerDtos(csvString) {
  const lines = csvString.trim().split("\n");

  // Skip header
  const dataLines = lines.slice(1);

  return dataLines.map((line) => {
    const cells = line.split(",").map(cell => cell.replace(/^"|"$/g, "").trim());

    // Assumes: ["", "Player", "Email", "Decklist"]
    const playerField = cells[1] || "";
    const email = cells[2] || "";

    let firstname = "";
    let lastname = "";
    const nameParts = playerField.split(" ");

    if (nameParts.length === 1) {
      firstname = nameParts[0];
    } else if (nameParts.length >= 2) {
      firstname = nameParts[0];
      lastname = nameParts.slice(1).join(" ");
    }

    const nickname = lastname ? `${firstname} ${lastname}` : firstname;

    return {
      nickname,
      firstname,
      lastname,
      email,
      playerId: null,
    };
  });
}