package ru.clevertec.news.config;

import ru.clevertec.news.caches.CacheProvider;

public interface CacheConfig <K,V>{

    public CacheProvider<K,V> getCache();
}
