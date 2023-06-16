package suu.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import suu.model.AirPollutionStatus;
import suu.model.ParkingSpace;
import suu.model.TrafficCongestion;
import suu.repository.AirPollutionRepository;
import suu.repository.ParkingRepository;
import suu.repository.TrafficRepository;

import java.util.List;

@RestController
@RequestMapping("/srodmiescie")
public class Edge2Controller {
    private final TrafficRepository trafficRepository;
    private final AirPollutionRepository airPollutionRepository;
    private final ParkingRepository parkingRepository;

    public Edge2Controller(TrafficRepository trafficRepository, AirPollutionRepository airPollutionRepository,
            ParkingRepository parkingRepository) {
        this.trafficRepository = trafficRepository;
        this.airPollutionRepository = airPollutionRepository;
        this.parkingRepository = parkingRepository;
    }

    @GetMapping("/traffic")
    public ResponseEntity<List<TrafficCongestion>> getTrafficAll() {
        return new ResponseEntity<>(trafficRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/traffic/{id}")
    public ResponseEntity<TrafficCongestion> getTrafficCongestionById(@PathVariable String id) {
        return new ResponseEntity<>(trafficRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new IllegalStateException("Edge2: traffic not found")), HttpStatus.OK);
    }

    @GetMapping("/traffic/average")
    public ResponseEntity<Double> getTrafficCongestionAverage() {
        List<TrafficCongestion> congestionList = trafficRepository.findAll();
        int totalCongestionIndex = congestionList.stream()
                .mapToInt(TrafficCongestion::getCongestionIndex)
                .sum();

        double average = totalCongestionIndex / (double) congestionList.size();

        return ResponseEntity.ok(average);
    }

    @GetMapping("/air/average")
    public ResponseEntity<AirPollutionStatus> getAirPollutionAverage() {
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
        result.setPM1((int) averagePM1);
        result.setPM10((int) averagePM10);
        result.setPM2_5((int) averagePM2_5);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/parking")
    public ResponseEntity<List<ParkingSpace>> getParkingAll() {
        return new ResponseEntity<>(parkingRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/parking/{id}")
    public ResponseEntity<ParkingSpace> getParkingById(@PathVariable String id) {
        return new ResponseEntity<>(parkingRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new IllegalStateException("Edge2: parking space not found")), HttpStatus.OK);
    }

    @GetMapping("/parking/average")
    public ResponseEntity<Double> getParkingAverage() {
        List<ParkingSpace> parkingList = parkingRepository.findAll();

        double averageParking = parkingList.stream()
                .map(ParkingSpace::getOccupied)
                .map(b -> b ? 1 : 0)
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0.0);

        return ResponseEntity.ok(averageParking);
    }
}
