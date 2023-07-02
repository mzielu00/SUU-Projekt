package suu.config;

import lombok.Data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties(prefix = "central")
@PropertySource("classpath:configMap.yaml")
@Data
public class ConfigMap {

    @Value("${central.edge1-ip}")
    public static String edge1Ip;

    @Value("${central.edge2-ip}")
    public static String edge2Ip;

    public String getEdge1Ip() {
        return edge1Ip;
    }

}
