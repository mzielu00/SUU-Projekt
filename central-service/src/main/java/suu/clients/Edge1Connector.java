package suu.clients;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import suu.model.AirPollutionStatus;

@Configuration
@PropertySource("classpath:configMap.yaml")
@ConfigurationProperties(prefix = "central")
@FeignClient(name = "edge1", url = "${central.edge1.ip}")
public interface Edge1Connector {
    @GetMapping("/edge/huta/air/average")
    AirPollutionStatus getAirPollutionAverage();

    @GetMapping("/edge/huta/traffic/average")
    Double getTrafficCongestionAverage();
}

