package de.balloncon.cedh_tool_backend.model;


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
