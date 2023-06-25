package suu.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import suu.model.AirPollutionStatus;

@FeignClient(name = "edge3", url = "http://localhost:8280")
public interface Edge3Connector {
    @GetMapping("/edge3/podgorze/air/average")
    AirPollutionStatus getAirPollutionAverageEdge3();

    @GetMapping("/edge3/podgorze/traffic/average")
    Double getTrafficCongestionAverageEdge3();
}

