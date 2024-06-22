package org.letscareer.letscareer;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@OpenAPIDefinition(servers = {@Server(url = "https://www.letscareer-test.shop", description = "Default Server url")})
@EnableScheduling
@SpringBootApplication
public class LetsCareerApplication {

    public static void main(String[] args) {
        SpringApplication.run(LetsCareerApplication.class, args);
    }

}
