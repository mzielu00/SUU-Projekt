package suu.model;


import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import suu.repository.AirPollutionRepository;
import suu.repository.TrafficRepository;

import java.util.concurrent.ThreadLocalRandom;

@Component
public class DataGenerator {

    @Autowired
    private TrafficRepository trafficRepository;

    @Autowired
    private AirPollutionRepository airPollutionRepository;

    @PostConstruct
    public void generateData() {

        for (int i = 0; i < 100; i++) {
            int traffic = generateRandomTrafficIndex();
            TrafficCongestion trafficCongestion = new TrafficCongestion();
            trafficCongestion.setCongestionIndex(traffic);
            trafficRepository.save(trafficCongestion);

            AirPollutionStatus airPollutionStatus = generateRandomAirPollutionStatus();
            airPollutionRepository.save(airPollutionStatus);
        }
    }

    private int generateRandomTrafficIndex(){
        return ThreadLocalRandom.current().nextInt(0, 300);
    }

    private static AirPollutionStatus generateRandomAirPollutionStatus() {
        int PM10_bound = 50;
        int PM2_5_bound = 25;
        int PM1_bound = 10;
        AirPollutionStatus airPollutionStatus = new AirPollutionStatus();
        airPollutionStatus.setPM10(ThreadLocalRandom.current().nextInt(1, PM10_bound));
        airPollutionStatus.setPM2_5(ThreadLocalRandom.current().nextInt(1, PM2_5_bound));
        airPollutionStatus.setPM1(ThreadLocalRandom.current().nextInt(1, PM1_bound));
        return airPollutionStatus;
    }
}

