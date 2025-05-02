package de.balloncon.cedh_tool_backend.pod;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${apiVersion}")
public class PodController {

    private final PodService podService;

    PodController(PodService podService) {
        this.podService = podService;
    }

    @GetMapping("pods")
    List<Pod> pods() {
        return podService.getAllPods();

    }
}
