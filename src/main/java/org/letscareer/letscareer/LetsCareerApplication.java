package org.letscareer.letscareer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LetsCareerApplication {

    public static void main(String[] args) {
        SpringApplication.run(LetsCareerApplication.class, args);
    }

}
