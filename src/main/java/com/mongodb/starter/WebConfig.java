package com.mongodb.starter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("https://expenses.bydallas.com") // specify the allowed origin
                .allowedMethods("GET", "POST", "PUT", "DELETE") // specify the allowed HTTP methods
                .allowedHeaders("*") // specify the allowed headers
                .allowCredentials(true) // specify if you want to allow credentials
                .maxAge(3600); // specify the max age of the CORS configuration
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000") // specify the allowed origin
                .allowedMethods("GET", "POST", "PUT", "DELETE") // specify the allowed HTTP methods
                .allowedHeaders("*") // specify the allowed headers
                .allowCredentials(true) // specify if you want to allow credentials
                .maxAge(3600); // specify the max age of the CORS configuration
    }
}
