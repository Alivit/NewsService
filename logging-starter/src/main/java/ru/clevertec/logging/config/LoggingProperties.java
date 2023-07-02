package ru.clevertec.logging.config;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * The LoggingProperties class for reading properties for config logging.
 * It provides methods to set up various settings logging configurations.
 */
@Data
@Slf4j
@NoArgsConstructor
@ConfigurationProperties(prefix = "app.logging")
public class LoggingProperties {

    /**
     * to enable logging in project
     */
    private boolean enabled;

    @PostConstruct
    void init(){
        log.info("Logging properties initializing: {}", this);
    }
}
