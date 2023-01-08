package de.htwberlin.webtech.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedMethods("*")
                .allowedOrigins(
                        "http://localhost:4040",
                        "http://localhost:4041",
                        "http://localhost:4042",
                        "http://localhost:4043",
                        "http://localhost:4044",
                        "http://localhost:4045",
                        "http://localhost:4046",
                        "http://localhost:4047",

                        "http://192.168.178.160:4040"
                );
    }
}
