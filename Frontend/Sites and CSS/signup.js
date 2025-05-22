import React, {useState} from "react";
import "./signup.css"; // Assuming styles go here

function Card({children}) {
  return <div className="pod">{children}</div>;
}

function CardContent({children, className}) {
  return <div className={className}>{children}</div>;
}

function Input({type, placeholder, value, onChange, className}) {
  return (
      <input
          type={type}
          placeholder={placeholder}
          value={value}
          onChange={onChange}
          className={`search-bar ${className}`}
      />
  );
}

function Button({type, children}) {
  return (
      <button type={type} className="submit-button">
        {children}
      </button>
  );
}

export default function TournamentSignup() {
  const [nickname, setNickname] = useState("");
  const [firstname, setFirstname] = useState("");
  const [lastname, setLastname] = useState("");
  const [players, setPlayers] = useState([]);
  const [successMessage, setSuccessMessage] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!nickname.trim() || !firstname.trim() || !lastname.trim()) {
      return;
    }

    try {
      const response = await fetch("http://127.0.0.1:8080/api/v1/player", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          accept: "*/*",
        },
        body: JSON.stringify({
          nickname,
          firstname,
          lastname,
          email: ""
        }),
      });

      if (response.ok) {
        setPlayers([
          ...players,
          {
            nickname,
            firstname,
            lastname,
            timestamp: new Date().toLocaleString(),
          },
        ]);
        setNickname("");
        setFirstname("");
        setLastname("");
        setSuccessMessage("Signup successful!");
        setTimeout(() => setSuccessMessage(""), 3000);
      } else {
        setSuccessMessage("Signup failed. Please try again.");
      }
    } catch (error) {
      setSuccessMessage("Network error. Please try again.");
    }
  };

  return (
      <div className="container">
        <h1 className="title">Tournament Signup</h1>
        <form onSubmit={handleSubmit} className="signup-form">
          <Input
              type="text"
              placeholder="Nickname"
              value={nickname}
              onChange={(e) => setNickname(e.target.value)}
          />
          <Input
              type="text"
              placeholder="First Name"
              value={firstname}
              onChange={(e) => setFirstname(e.target.value)}
          />
          <Input
              type="text"
              placeholder="Last Name"
              value={lastname}
              onChange={(e) => setLastname(e.target.value)}
          />
          <Button type="submit">Sign Up</Button>
        </form>

        {successMessage && (
            <p style={{
              textAlign: "center",
              color: "#4caf50"
            }}>{successMessage}</p>
        )}

        <div className="pods-grid">
          {players.map((player, index) => (
              <Card key={index}>
                <CardContent>
                  <h2>{player.nickname}</h2>
                  <p>{player.firstname} {player.lastname}</p>
                  <p>Signed up at: {player.timestamp}</p>
                </CardContent>
              </Card>
          ))}
        </div>
      </div>
  );
}
