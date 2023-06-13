package ru.clevertec.news.caches;

public interface CacheProvider<K,V> {

    public V get(K key);

    public void put(K key, V value);

    public void delete(K key);

    int size();

    boolean isEmpty();

    boolean containsValue(V value);

    boolean containsKey(K key);

    void clear();
}
