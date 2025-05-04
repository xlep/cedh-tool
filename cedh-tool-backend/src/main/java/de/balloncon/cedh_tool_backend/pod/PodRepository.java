package de.balloncon.cedh_tool_backend.pod;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PodRepository extends JpaRepository<Pod, UUID> {
}
