# Habitrackus keep track of your daily Habit Backend
[![CI](https://github.com/maxruffo/WebTechProjekt_Max/actions/workflows/mainCI.yml/badge.svg)](https://github.com/maxruffo/WebTechProjekt_Max/actions/workflows/mainCI.yml)
## Description
<strong>Note: your find the complete description in the Frontend Repository https://github.com/maxruffo/Habitrackus_Frontend<strong>
## Getting Started
1. In your Enviroment specify the local Variables $JDBC_DATABASE_USERNAME and $JDBC_DATABASE_PASSWORD and the Url route
```yaml
spring:
  datasource:
    url: ${JDBC_DATABASE_URL}
    username: ${DATASOURCE_USERNAME}
    password: ${DATASOURCE_PASSWORD}
    driverClassName: org.h2.Driver
  h2:
```
2. Before you can Use this Application an h2 Databse must be initialized
3. If you changed the LocalHost port in the Frontend please insert the localport in the WebConfig file:
```java
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
```
3. to start the Backend just run the WebtechApplication module
