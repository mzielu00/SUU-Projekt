package suu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@RibbonClient(name = "edge1")
public class EdgeApp {
    public static void main(String[] args) {
        SpringApplication.run(EdgeApp.class, args);
        System.out.println("Edge1 started...!!!");
    }}