package suu.model;


import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import suu.repository.TrafficRepository;

import java.util.concurrent.ThreadLocalRandom;

@Component
public class DataGenerator {

    @Autowired
    private TrafficRepository trafficRepository;

    @PostConstruct
    public void generateData() {

        for (int i = 0; i < 50; i++) {
            int traffic = getRandomTrafficIndex();
            TrafficCongestion trafficCongestion = new TrafficCongestion();
            trafficCongestion.setCongestionIndex(traffic);
            trafficRepository.save(trafficCongestion);
        }
    }

    private int getRandomTrafficIndex(){
        return ThreadLocalRandom.current().nextInt(0, 300);
    }
}

