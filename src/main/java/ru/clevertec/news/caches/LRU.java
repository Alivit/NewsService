package ru.clevertec.news.caches;

import lombok.Getter;

import java.util.HashMap;
import java.util.LinkedList;

@Getter
public class LRU<K, V> implements CacheProvider<K, V> {

    private final long capacity;
    private HashMap<K, V> storage;
    private LinkedList<K> queue;

    public LRU(long capacity) {
        this.capacity = capacity;
        storage = new HashMap<>();
        queue = new LinkedList<>();
    }

    @Override
    public V get(K key) {
        if(!storage.containsKey(key)){
            return null;
        }
        queue.remove(key);
        queue.addFirst(key);

        return storage.get(key);
    }

    @Override
    public void put(K key, V value) {
        if(storage.containsKey(key)){
            delete(key);
            put(key, value);
        }

        if (queue.size() >= capacity) {
            K keyRemoved = queue.removeLast();
            storage.remove(keyRemoved);
        }

        queue.addFirst(key);
        storage.put(key, value);
    }

    @Override
    public void delete(K key) {
        storage.remove(key);
        queue.remove(key);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public boolean isEmpty() {
        return storage.isEmpty();
    }

    @Override
    public boolean containsValue(V value) {
        return storage.containsValue(value);
    }

    @Override
    public boolean containsKey(K key) {
        return storage.containsKey(key);
    }

    @Override
    public void clear() {
        storage.clear();
        queue.clear();
    }

}
