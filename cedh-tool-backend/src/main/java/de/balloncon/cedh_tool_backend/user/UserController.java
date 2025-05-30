package de.balloncon.cedh_tool_backend.user;

import de.balloncon.cedh_tool_backend.dto.LoginRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${apiVersion}/user")
@Tag(name = "User Controller", description = "Operations related to user management and login.")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @Operation(summary = "Simple login verification")
  @PostMapping("/login")
  public ResponseEntity<Boolean> login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
    boolean isValid = userService.verifyLogin(loginRequestDto.getUsername(), loginRequestDto.getPassword());
    return ResponseEntity.ok(isValid);
  }

  @Operation(summary = "Register new user", security = @SecurityRequirement(name = "basicAuth"))
  @PostMapping("/register")
  public ResponseEntity<String> register(@Valid @RequestBody LoginRequestDto loginRequestDto) {
    return userService.registerUser(loginRequestDto.getUsername(), loginRequestDto.getPassword());
  }
}