package de.balloncon.cedh_tool_backend.apiuser;

import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ApiUserDetailsService implements UserDetailsService {

  private final ApiUserRepository userRepo;

  public ApiUserDetailsService(ApiUserRepository userRepo) {
    this.userRepo = userRepo;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    ApiUser user = userRepo.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    return new org.springframework.security.core.userdetails.User(
        user.getUsername(),
        user.getPassword(),  // plaintext password
        List.of(new SimpleGrantedAuthority("ROLE_APIUSER"))
    );
  }
}
