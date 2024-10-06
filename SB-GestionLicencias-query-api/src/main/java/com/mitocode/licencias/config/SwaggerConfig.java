package com.mitocode.licencias.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Gestión de licencias API")
                        .description("Este API realiza la gestión y emisión de licencias.")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Rodrigo")
                                .url("https://mitocode.com")
                                .email("r4mirez.r@gmail.com"))
                        .license(new License()
                                .name("LICENSE")
                                .url("LICENSE URL")))
                .servers(List.of(new Server()
                        .url("http://localhost:9050")
                        .description("Servidor de desarrollo"))
                );
    }

}