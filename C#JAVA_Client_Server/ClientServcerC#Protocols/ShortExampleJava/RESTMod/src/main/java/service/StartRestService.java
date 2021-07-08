package service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("hibernatesRepo")
@ComponentScan("service")
@SpringBootApplication
public class StartRestService {
    public static void main(String[] args) {
        SpringApplication.run(StartRestService.class,args);
    }
}
