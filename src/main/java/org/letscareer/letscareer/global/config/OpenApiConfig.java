//package org.letscareer.letscareer.global.config;
//
//import io.swagger.v3.oas.models.Components;
//import io.swagger.v3.oas.models.OpenAPI;
//import io.swagger.v3.oas.models.info.Info;
//import io.swagger.v3.oas.models.servers.Server;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.Arrays;
//
//@Configuration
//public class OpenApiConfig {
//    @Value("${server.url}")
//    private String SERVER_URL;
//
//    @Bean
//    public OpenAPI openApi(@Value("${springdoc.version}") String springdocVersion) {
//        Info info = new Info()
//                .title("Let's Carrer API")
//                .version(springdocVersion)
//                .description("렛츠커리어 API 입니다");
//
//        return new OpenAPI()
//                .servers(Arrays.asList(
//                        new Server().url(SERVER_URL)
//                ))
//                .components(new Components())
//                .info(info);
//    }
//}
