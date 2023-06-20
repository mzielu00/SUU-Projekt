package suu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import suu.clients.Edge1Connector;
import suu.model.AirPollutionStatus;

@RestController
@RequestMapping("/data")
public class CentralController {
    @Autowired
    private final Edge1Connector edge1Connector;

    public CentralController(Edge1Connector edge1Connector) {
        this.edge1Connector = edge1Connector;
    }

    @GetMapping("/traffic")
    public ResponseEntity<Double> getAverageTrafficCongestion() {
        Double edge1Traffic = edge1Connector.getTrafficCongestionAverageEdge1();

        return ResponseEntity.ok(edge1Traffic);
    }

    @GetMapping("/air")
    public ResponseEntity<AirPollutionStatus> getAverageAirPollution() {
        AirPollutionStatus airPollutionStatus = edge1Connector.getAirPollutionAverageEdge1();

        return ResponseEntity.ok(airPollutionStatus);
    }
}
