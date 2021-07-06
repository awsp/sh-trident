package com.areamode.shtrident;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class SHTridentApplication {

    public static void main(String[] args) {
        SpringApplication.run(SHTridentApplication.class, args);
    }

    @Bean
    ApplicationListener<ApplicationReadyEvent> onReady() {
        return event -> {
            log.info("Seed");
        };
    }

}