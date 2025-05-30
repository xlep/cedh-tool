package de.balloncon.cedh_tool_backend.apiuser;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ApiUser {

  @Id
  private String username;

  @Column(nullable = false)
  private String password;  // plaintext for simplicity

  // constructors, getters, setters
  public ApiUser() {}

  public ApiUser(String username, String password) {
    this.username = username;
    this.password = password;
  }
}
