package suu.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import suu.model.AirPollutionStatus;

@FeignClient(name = "edge2", url = "http://localhost:8270")
public interface Edge2Connector {
    @GetMapping("/edge2/nowa-huta/air/average")
    AirPollutionStatus getAirPollutionAverageEdge2();

    @GetMapping("/edge2/nowa-huta/traffic/average")
    Double getTrafficCongestionAverageEdge2();
}

