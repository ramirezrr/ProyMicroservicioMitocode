package com.mitocode.licencias;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class SbGestionLicenciasApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SbGestionLicenciasApiApplication.class, args);
    }

}
