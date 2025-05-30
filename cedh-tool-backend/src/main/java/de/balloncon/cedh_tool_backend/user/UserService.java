package de.balloncon.cedh_tool_backend.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserService {
  @Autowired
  UserRepository userRepository;
  private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  public ResponseEntity<String> registerUser(String username, String rawPassword) {
    String hashedPassword = passwordEncoder.encode(rawPassword);
    User user = new User();
    user.setUsername(username);
    user.setPassword(hashedPassword);

    if (userRepository.findByUsername(username) != null) {
      return ResponseEntity.badRequest().body("Username already taken");
    }

    try {
      userRepository.save(user);
      return ResponseEntity.ok("Registration successful");
    } catch (Exception e) {
      // Generic fallback for unexpected errors
      return ResponseEntity.status(500).body("Internal server error");
    }
  }

  public boolean verifyLogin(String username, String rawPassword) {
    User user = userRepository.findByUsername(username);
    return user != null && passwordEncoder.matches(rawPassword, user.getPassword());
  }

}
