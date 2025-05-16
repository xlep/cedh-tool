package de.balloncon.cedh_tool_backend.seat;

import de.balloncon.cedh_tool_backend.dto.PodResultDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("${apiVersion}")
public class SeatController {

  private final SeatService seatService;

  SeatController(SeatService seatService) {
    this.seatService = seatService;
  }

}
