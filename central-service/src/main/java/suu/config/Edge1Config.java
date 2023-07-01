package suu.config;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Edge1Config {
    @Value("${feign.client.config.edge1.url}")
    private String applicationAUrl;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.target(applicationAUrl);
        };
    }
}
