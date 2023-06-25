package suu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import suu.clients.Edge1Connector;
import suu.clients.Edge2Connector;
import suu.clients.Edge3Connector;
import suu.model.AirPollutionStatus;

@RestController
@RequestMapping("/data")
public class CentralController {
    private int edgesNumber = 3;

    @Autowired
    private final Edge1Connector edge1Connector;

    @Autowired
    private final Edge2Connector edge2Connector;

    @Autowired
    private final Edge3Connector edge3Connector;

    public CentralController(Edge1Connector edge1Connector, Edge2Connector edge2Connector, Edge3Connector edge3Connector) {
        this.edge1Connector = edge1Connector;
        this.edge2Connector = edge2Connector;
        this.edge3Connector = edge3Connector;
    }

    @GetMapping("/traffic")
    public ResponseEntity<Double> getAverageTrafficCongestion() {
        Double edge1Traffic = edge1Connector.getTrafficCongestionAverageEdge1();
        Double edge2Traffic = edge2Connector.getTrafficCongestionAverageEdge2();
        Double edge3Traffic = edge3Connector.getTrafficCongestionAverageEdge3();
        Double average = (edge1Traffic + edge2Traffic + edge3Traffic)/edgesNumber;
        return ResponseEntity.ok(average);
    }

    @GetMapping("/air")
    public ResponseEntity<AirPollutionStatus> getAverageAirPollution() {
        AirPollutionStatus airPollutionStatus1 = edge1Connector.getAirPollutionAverageEdge1();
        AirPollutionStatus airPollutionStatus2 = edge2Connector.getAirPollutionAverageEdge2();
        AirPollutionStatus airPollutionStatus3 = edge3Connector.getAirPollutionAverageEdge3();

        int averagePM10 = (airPollutionStatus1.getPM10() + airPollutionStatus2.getPM10() + airPollutionStatus3.getPM10()) / edgesNumber;
        int averagePM2_5 = (airPollutionStatus1.getPM2_5() + airPollutionStatus2.getPM2_5() + airPollutionStatus3.getPM2_5()) / edgesNumber;
        int averagePM1 = (airPollutionStatus1.getPM1() + airPollutionStatus2.getPM1() + airPollutionStatus3.getPM1()) / edgesNumber;

        AirPollutionStatus averageAirPollutionStatus = new AirPollutionStatus();
        averageAirPollutionStatus.setPM10(averagePM10);
        averageAirPollutionStatus.setPM2_5(averagePM2_5);
        averageAirPollutionStatus.setPM1(averagePM1);

        return ResponseEntity.ok(averageAirPollutionStatus);
    }
}
