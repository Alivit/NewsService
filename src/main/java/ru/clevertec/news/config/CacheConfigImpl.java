package ru.clevertec.news.config;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import ru.clevertec.news.caches.CacheProvider;
import ru.clevertec.news.caches.LFU;
import ru.clevertec.news.caches.LRU;

@Data
@Slf4j
@Configuration
@ConfigurationProperties(prefix = "cache")
public class CacheConfigImpl<K, V> implements CacheConfig<K,V>{

    private String algorithm;
    private Long capacity;

    @PostConstruct
    void init(){
        log.info("Cache properties initializing: {}", this);
    }

    @Override
    public CacheProvider<K, V> getCache() {
        if("LRU".equalsIgnoreCase(algorithm)){
            return new LRU<>(capacity);
        }else {
            return new LFU<>(capacity);
        }
    }

}
