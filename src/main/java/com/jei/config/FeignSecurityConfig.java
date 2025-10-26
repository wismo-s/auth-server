package com.jei.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignSecurityConfig {

    @Bean
    public RequestInterceptor bearerTokenInterceptor() {
        return template -> {
            // Por ahora no agrega token
            // Más adelante podrás activar esto:
            // var auth = SecurityContextHolder.getContext().getAuthentication();
            // if (auth instanceof JwtAuthenticationToken jwt) {
            //     template.header("Authorization", "Bearer " + jwt.getToken().getTokenValue());
            // }
        };
    }
}
