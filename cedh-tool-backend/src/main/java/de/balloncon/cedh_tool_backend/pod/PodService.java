package de.balloncon.cedh_tool_backend.pod;

import de.balloncon.cedh_tool_backend.player.Player;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PodService {

    public List<Pod> getAllPods() {
        Player p1 = new Player("1", "Raphi", "", "");
        Player p2 = new Player("2", "Sigi", "", "");
        Player p3 = new Player("3", "Tino", "", "");
        Player p4 = new Player("4", "Moe", "", "");


        Pod pod = new Pod();
        pod.setSeatOne(p1);
        pod.setSeatTwo(p2);
        pod.setSeatThree(p3);
        pod.setSeatFour(p4);

        List<Pod> pods = new ArrayList<Pod>();
        pods.add(pod);

        return  pods;
    }
}
