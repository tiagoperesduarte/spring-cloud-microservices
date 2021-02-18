package com.ecommerce.gatewayserver.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringCloudConfig {
    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r
                        .path("/api/auth/**")
                        .uri("lb://auth-service")
                )
                .route(r -> r
                        .path("/api/users/**")
                        .uri("lb://auth-service")
                )
                .route(r -> r
                        .path("/api/products/**")
                        .uri("lb://product-service")
                )
                .route(r -> r
                        .path("/api/orders/**")
                        .uri("lb://order-service")
                )
                .build();
    }
}
