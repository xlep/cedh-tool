package de.balloncon.cedh_tool_backend.seat;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("${apiVersion}")
public class SeatController {

  private final SeatService seatService;

  SeatController(SeatService seatService) {
    this.seatService = seatService;
  }

}
