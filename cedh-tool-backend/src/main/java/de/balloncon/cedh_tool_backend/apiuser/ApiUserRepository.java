package de.balloncon.cedh_tool_backend.apiuser;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiUserRepository extends JpaRepository<ApiUser, String> {
  Optional<ApiUser> findByUsername(String username);
}
