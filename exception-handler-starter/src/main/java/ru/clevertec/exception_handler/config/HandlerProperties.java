package ru.clevertec.exception_handler.config;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import ru.clevertec.exception_handler.handler.ControllerErrorHandler;

@Data
@Slf4j
@NoArgsConstructor
@ConfigurationProperties(prefix = "app.handler.exception")
public class HandlerProperties {

    /**
     * to enable handler exception in project
     */
    private boolean enabled;

    @PostConstruct
    void init(){
        log.info("Handler properties initializing: {}", this);
    }

    @Bean
    @ConditionalOnMissingBean(ControllerErrorHandler.class)
    public ControllerErrorHandler controllerErrorHandler(){
        return new ControllerErrorHandler();
    }

}
