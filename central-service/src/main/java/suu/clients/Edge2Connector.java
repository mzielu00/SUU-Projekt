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
@FeignClient(name = "edge2", url = "${central.edge2.ip}")
public interface Edge2Connector {
    @GetMapping("/edge/midtown/air/average")
    AirPollutionStatus getAirPollutionAverage();

}

