package com.mitocode.microservices.cloud_gateway.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


@Slf4j
@Component
public class CustomFilter extends AbstractGatewayFilterFactory<CustomFilter.CustomConfiguration> {

    public CustomFilter() {
        super(CustomConfiguration.class);
    }

    @Override
    public String name() {
        return "LicenciaServiceFilter";
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("headerKey", "headerValue");
    }

    @Override
    public GatewayFilter apply(CustomConfiguration config) {

        // Prefilter
        log.info("[CustomFilter] - [apply]: Prefilter");
        Long startime = System.currentTimeMillis();

        return ((exchange, chain) -> chain.filter(exchange).then(Mono.fromRunnable(() -> {
            // PostFilter
            log.info("[CustomFilter] - [apply]: Postfilter");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String fecha = formatter.format(new Date());
            log.info("FECHA: " + fecha);

            // Agrega el header con la fecha
            exchange.getResponse().getHeaders().add("Fecha", fecha);
            exchange.getResponse().getHeaders().add("Usuario", "UserMitocode");

            // Agrega otros headers si es necesario
            exchange.getResponse().getHeaders().add(config.headerKey, config.headerValue);
        })));

    }



    @Data
    public static class CustomConfiguration {
        private String headerKey;
        private String headerValue;
    }

}
