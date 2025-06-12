package de.balloncon.cedh_tool_backend.seat;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${apiVersion}")
@Tag(name = "Seat Controller", description = "Operations related to seat management.")
public class SeatController {

  private final SeatService seatService;

  SeatController(SeatService seatService) {
    this.seatService = seatService;
  }

}


