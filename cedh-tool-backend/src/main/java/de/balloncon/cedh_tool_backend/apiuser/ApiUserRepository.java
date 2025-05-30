package de.balloncon.cedh_tool_backend.apiuser;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ApiUserRepository extends JpaRepository<ApiUser, String> {
  Optional<ApiUser> findByUsername(String username);
}