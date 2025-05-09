package de.balloncon.cedh_tool_backend.seat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class SeatService {

    @Autowired
    SeatRepository seatRepository;

    public Optional<List<Seat>> getSeatsByPodId(UUID podId) {
        List<Seat> seats = seatRepository.findByPodId(podId);
        return seats.isEmpty() ? Optional.empty() : Optional.of(seats);
    }
}
