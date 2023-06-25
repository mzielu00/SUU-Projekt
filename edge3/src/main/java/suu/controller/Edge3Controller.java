package suu.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import suu.model.AirPollutionStatus;
import suu.model.TrafficCongestion;
import suu.repository.AirPollutionRepository;
import suu.repository.TrafficRepository;

import java.util.List;

@RestController
@RequestMapping("/podgorze")
public class Edge3Controller {
    private final TrafficRepository trafficRepository;
    private final AirPollutionRepository airPollutionRepository;
    public Edge3Controller(TrafficRepository trafficRepository, AirPollutionRepository airPollutionRepository) {
        this.trafficRepository = trafficRepository;
        this.airPollutionRepository = airPollutionRepository;
    }

    @GetMapping ("/traffic")
    public ResponseEntity<List<TrafficCongestion>> getAll() {
        return new ResponseEntity<>(trafficRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/traffic/{id}")
    public ResponseEntity<TrafficCongestion> getTrafficCongestionById(@PathVariable String id) {
        return new ResponseEntity<>(trafficRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new IllegalStateException("Edge1: traffic not found")), HttpStatus.OK);
    }

    @GetMapping("/traffic/average")
    public ResponseEntity<Double> getAverageTrafficCongestion() {
        List<TrafficCongestion> congestionList = trafficRepository.findAll();
        int totalCongestionIndex = congestionList.stream()
                .mapToInt(TrafficCongestion::getCongestionIndex)
                .sum();

        double average = totalCongestionIndex / (double) congestionList.size();

        return ResponseEntity.ok(average);
    }

    @GetMapping("/air/average")
    public ResponseEntity<AirPollutionStatus> getAverageAirPollution() {
        List<AirPollutionStatus> airPollutionStatusList = airPollutionRepository.findAll();

        double averagePM10 = airPollutionStatusList.stream()
                .mapToInt(AirPollutionStatus::getPM10)
                .average()
                .orElse(0.0);

        double averagePM2_5 = airPollutionStatusList.stream()
                .mapToInt(AirPollutionStatus::getPM2_5)
                .average()
                .orElse(0.0);

        double averagePM1 = airPollutionStatusList.stream()
                .mapToInt(AirPollutionStatus::getPM1)
                .average()
                .orElse(0.0);

        AirPollutionStatus result = new AirPollutionStatus();
        result.setPM1((int)averagePM1);
        result.setPM10((int)averagePM10);
        result.setPM2_5((int)averagePM2_5);

        return ResponseEntity.ok(result);
    }
}
