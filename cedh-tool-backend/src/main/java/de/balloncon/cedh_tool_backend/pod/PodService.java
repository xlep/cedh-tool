package de.balloncon.cedh_tool_backend.pod;

import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class PodService {

    @Autowired
    private PodRepository podRepository;

    public Pod getPodById( UUID podId) {
        return podRepository.findById(podId).orElse(null);
    }
}
