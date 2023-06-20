package suu.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import suu.model.AirPollutionStatus;

@FeignClient(name = "edge1", url = "http://localhost:8260")
public interface Edge1Connector {
    @GetMapping("/edge1/srodmiescie/air/average")
    AirPollutionStatus getAirPollutionAverageEdge1();

    @GetMapping("/edge1/srodmiescie/traffic/average")
    Double getTrafficCongestionAverageEdge1();
}

