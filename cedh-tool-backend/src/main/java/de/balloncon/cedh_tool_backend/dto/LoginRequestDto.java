package de.balloncon.cedh_tool_backend.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class LoginRequestDto {
  private String username;
  private String password;

}
