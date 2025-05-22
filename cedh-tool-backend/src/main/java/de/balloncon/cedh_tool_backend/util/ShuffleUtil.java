package de.balloncon.cedh_tool_backend.util;

import java.security.SecureRandom;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ShuffleUtil {

  private final SecureRandom random = new SecureRandom();

  public <T> void shuffle(List<T> list) {
    for (int i = list.size() - 1; i > 0; i--) {
      int j = random.nextInt(i + 1);
      T temp = list.get(i);
      list.set(i, list.get(j));
      list.set(j, temp);
    }
  }
}
