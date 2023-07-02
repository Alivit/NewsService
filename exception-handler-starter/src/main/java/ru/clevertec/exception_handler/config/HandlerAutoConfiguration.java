package ru.clevertec.exception_handler.config;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The HandlerAutoConfiguration class is responsible for configuring
 * the application and its components.
 * It provides methods to set up various settings handler exception configurations.
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(HandlerProperties.class)
@ConditionalOnClass(HandlerProperties.class)
@ConditionalOnProperty(prefix = "app.handler.exception", name = "enabled", havingValue = "true")
public class HandlerAutoConfiguration {

    @PostConstruct
    void init(){
        log.info("HandlerAutoConfiguration initialized");
    }


}
