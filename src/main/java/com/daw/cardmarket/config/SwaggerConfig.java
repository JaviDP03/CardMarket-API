package com.daw.cardmarket.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
// URL: http://localhost:8080/swagger-ui/index.html
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("CardMarket API")
                        .version("0.1.0")
                        .description("Documentación de la API"));
    }
}

