package com.maerks.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Slf4j
@Configuration
@EnableSwagger2
@ComponentScan(basePackages = "com.maerks")
public class AppConfig  {
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplateBuilder().build();
    }
}
