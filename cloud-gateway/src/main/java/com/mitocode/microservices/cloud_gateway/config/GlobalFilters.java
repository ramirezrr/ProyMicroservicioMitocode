package com.mitocode.microservices.cloud_gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Slf4j
@Component
public class GlobalFilters implements GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // Prefilter
        log.info("[GlobalFilters] - [filter]: Prefilter");
        Long startime = System.currentTimeMillis();

        Optional<String> appCallerName = Optional.ofNullable(exchange.getRequest().getHeaders().getFirst("appCallerName")) ;
        if(appCallerName.isEmpty()){
            exchange.getRequest().mutate().header("appCallerName", "CloudGateway");
        }

        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            // PostFilter
            log.info("[GlobalFilters] - [filter]: Postfilter");
            log.info("Time elapsed: " + (System.currentTimeMillis() - startime) + " ms");

        }));
    }
}
