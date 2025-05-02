package de.balloncon.cedh_tool_backend.pod;


import de.balloncon.cedh_tool_backend.player.Player;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Pod {

   Player seatOne;
   Player seatTwo;
   Player seatThree;
   Player seatFour;
}
