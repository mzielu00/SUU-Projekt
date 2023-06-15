package suu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CentralService {
    public static void main(String[] args) {
        SpringApplication.run(CentralService.class, args);
        System.out.println("Central service started...!!!");
    }
}