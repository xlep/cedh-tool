package de.balloncon.cedh_tool_backend.util;

import de.balloncon.cedh_tool_backend.seat.Seat;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Component;

@Component
public class ShuffleUtil {

  private final SecureRandom random = new SecureRandom();

  public <T> void shuffleList(List<T> list) {
    for (int i = list.size() - 1; i > 0; i--) {
      int j = random.nextInt(i + 1);
      T temp = list.get(i);
      list.set(i, list.get(j));
      list.set(j, temp);
    }
  }

  public void randomizeSeatNumbers(Set<Seat> seats) {
    if (seats == null || seats.isEmpty()) return;

    List<Seat> seatList = new ArrayList<>(seats);

    List<Integer> seatNumbers = new ArrayList<>();
    for (int i = 1; i <= seatList.size(); i++) {
      seatNumbers.add(i);
    }

    shuffleList(seatNumbers);

    for (int i = 0; i < seatList.size(); i++) {
      seatList.get(i).setSeat((seatNumbers.get(i)));
    }
  }
}
