package suu.clients;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import suu.model.AirPollutionStatus;

@Configuration
@PropertySource("classpath:configMap.yaml")
@ConfigurationProperties(prefix = "central")
@FeignClient(name = "edge1", url = "${central.edge1-ip}")
public interface Edge1Connector {

    @GetMapping("/edge1/srodmiescie/air/average")
    AirPollutionStatus getAirPollutionAverageEdge1();

    @GetMapping("/edge1/srodmiescie/traffic/average")
    Double getTrafficCongestionAverageEdge1();
}

