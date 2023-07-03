package suu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import suu.clients.Edge1Connector;
import suu.clients.Edge2Connector;
import suu.model.AirPollutionStatus;

import java.lang.reflect.Array;

@RestController
@RequestMapping("/data")
public class CentralController {
    @Autowired
    private final Edge1Connector edge1Connector;
    @Autowired
    private final Edge2Connector edge2Connector;

    public CentralController(Edge1Connector edge1Connector, Edge2Connector edge2Connector) {
        this.edge1Connector = edge1Connector;
        this.edge2Connector = edge2Connector;
    }

    @GetMapping("/")
    public String hello() {
        return "Hello World!";
    }

    @GetMapping("/traffic")
    public ResponseEntity<Double> getAverageTrafficCongestion() {
        Double edge1Traffic = edge1Connector.getTrafficCongestionAverage();

        return ResponseEntity.ok(edge1Traffic);
    }

    @GetMapping("/air")
    public ResponseEntity<AirPollutionStatus[]> getAverageAirPollution() {
        AirPollutionStatus airPollutionStatus1 = edge1Connector.getAirPollutionAverage();
        AirPollutionStatus airPollutionStatus2 = edge2Connector.getAirPollutionAverage();


        return ResponseEntity.ok(new AirPollutionStatus[]{airPollutionStatus1, airPollutionStatus2});
    }
}
