package de.balloncon.cedh_tool_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class CedhToolBackendApplication {

  public static void main(String[] args) {
    String rawPassword = "12345678910";
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    String hashed = encoder.encode(rawPassword);
    System.out.println("Hashed password: " + hashed);
    SpringApplication.run(CedhToolBackendApplication.class, args);
  }

}
