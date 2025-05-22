package de.balloncon.cedh_tool_backend.mapper;

import de.balloncon.cedh_tool_backend.dto.PodDto;
import de.balloncon.cedh_tool_backend.pod.Pod;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class PodMapper {

  private final PlayerMapper playerMapper;

  public PodMapper(PlayerMapper playerMapper) {
    this.playerMapper = playerMapper;
  }

  public PodDto toDto(Pod pod) {
    return new PodDto(
        pod.getId(),
        pod.getTournament().getId(),
        pod.getName(),
        playerMapper.toDto(pod.getPlayers()));
  }

  public List<PodDto> toDto(List<Pod> pods) {
    List<PodDto> podDtos = new ArrayList<>();
    for (Pod pod : pods) {
      podDtos.add(toDto(pod));
    }
    return podDtos;
  }

}
