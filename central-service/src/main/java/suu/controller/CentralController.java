package suu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import suu.clients.Edge1Connector;
import suu.clients.Edge2Connector;
import suu.model.AirPollutionStatus;

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

    @GetMapping("/traffic")
    public ResponseEntity<Double> getAverageTrafficCongestion() {
        Double edge1Traffic = edge1Connector.getTrafficCongestionAverageEdge1();
        Double edge2Traffic = edge2Connector.getTrafficCongestionAverageEdge2();
        Double average = (edge1Traffic + edge2Traffic)/2;
        return ResponseEntity.ok(average);
    }

    @GetMapping("/air")
    public ResponseEntity<AirPollutionStatus> getAverageAirPollution() {
        AirPollutionStatus airPollutionStatus1 = edge1Connector.getAirPollutionAverageEdge1();
        AirPollutionStatus airPollutionStatus2 = edge2Connector.getAirPollutionAverageEdge2();

        return ResponseEntity.ok(airPollutionStatus1);
    }
}
