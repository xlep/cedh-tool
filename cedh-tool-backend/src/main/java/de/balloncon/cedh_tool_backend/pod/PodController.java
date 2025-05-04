package de.balloncon.cedh_tool_backend.pod;

import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("${apiVersion}")
public class PodController {

    private final PodService podService;

    PodController(PodService podService) {
        this.podService = podService;
    }

    @GetMapping("pods")
    Pod pods(@Parameter UUID podId) {
        return podService.getPodById(podId);
    }
}
