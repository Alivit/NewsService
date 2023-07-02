package ru.clevertec.news.caches;

/**
 * The CacheProvider interface is responsible for providing caching functionality to the application.
 * It defines methods for storing, retrieving, and removing data from the cache.
 */
public interface CacheProvider<K,V> {

    /**
     * Method Retrieving the value associated with a given key from the cache.
     *
     * @param key The key of object for communicating with storage.
     * @return object.
     */
    public V get(K key);

    /**
     * Method for storing a key-value pair in the cache.
     *
     * @param key The key of object for communicating with storage.
     * @param value The value for storing in storage.
     */
    public void put(K key, V value);

    /**
     * Method for removing a key-value pair from the cache.
     *
     * @param key The key of object for storing in storage.
     */
    public void delete(K key);

    int size();

    boolean isEmpty();

    boolean containsValue(V value);

    boolean containsKey(K key);

    void clear();
}
