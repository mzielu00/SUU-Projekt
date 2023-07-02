package suu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@RibbonClient(name = "edge1")
public class Edge1App {
    public static void main(String[] args) {
        SpringApplication.run(Edge1App.class, args);
        System.out.println("Edge1 started...!!!");
    }}