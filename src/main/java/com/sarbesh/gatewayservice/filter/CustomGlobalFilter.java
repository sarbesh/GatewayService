package com.sarbesh.gatewayservice.filter;

import com.sarbesh.core.config.NetworkAccessProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.UUID;


public class CustomGlobalFilter implements GlobalFilter, Ordered {

    @Autowired
    private NetworkAccessProperties networkAccessProperties;

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomGlobalFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        LOGGER.info("#CustomGlobalFilter Adding Global filer for Network access header");

        ServerHttpRequest.Builder mutateRequest = exchange.getRequest().mutate()
                .header(networkAccessProperties.getHeader(), networkAccessProperties.getSecret());

        if(!exchange.getRequest().getHeaders().containsKey("uuid")){
            if(MDC.get("UUID")==null){
                String uuid = UUID.randomUUID().toString();
                LOGGER.info("#CustomGlobalFilter Request missing uuid header, generated {} and adding",uuid);
                MDC.put("UUID",uuid);
            }
            mutateRequest.header("uuid",MDC.get("UUID") );
        }

        exchange.mutate().request(mutateRequest.build()).build();

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1;
    }

}
