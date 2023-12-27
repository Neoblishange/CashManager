package com.cash.back_cash_manager.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Value("${MY_SECRET_KEY}")
    private String secretKeyString;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtInterceptor(secretKeyString))
                .excludePathPatterns("/users/login", "/users/register", "/v3/api-docs/**", "/swagger-resources/**", "/swagger-ui/**", "/webjars/**")
                .addPathPatterns("/**");
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info().title("Your API").version("1.0.0"));
    }
}

