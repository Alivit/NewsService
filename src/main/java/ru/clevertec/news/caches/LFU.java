package ru.clevertec.news.caches;

import lombok.Getter;

import java.util.HashMap;
import java.util.LinkedHashSet;

@Getter
public class LFU<K, V> implements CacheProvider<K, V> {

    private final long capacity;
    private Long min = -1L;
    private HashMap<K, V> storage;
    private HashMap<K, Long> counters;
    private HashMap<Long, LinkedHashSet<K>> lists;

    public LFU(long capacity) {
        this.capacity = capacity;
        storage = new HashMap<>();
        counters = new HashMap<>();
        lists = new HashMap<>();
        lists.put(1L, new LinkedHashSet<>());
    }

    @Override
    public V get(K key) {
        if(!storage.containsKey(key)) {
            return null;
        }
        Long count = counters.get(key); // получения счётчика по ключу
        counters.put(key, count + 1); // увелечение счётчика
        lists.get(count).remove(key); // удаление старого счётчика

        if (count.equals(min) && lists.get(count).size() == 0){
            min++;
        }
        if (!lists.containsKey(count + 1)){
            lists.put(count + 1, new LinkedHashSet<>());
        }
        lists.get(count + 1).add(key);
        return storage.get(key);
    }

    @Override
    public void put(K key, V value) {
        if (capacity <= 0) {
            return;
        }
        if (storage.containsKey(key)){
            storage.put(key, value);
            get(key);
            return;
        }
        if (storage.size() >= capacity){
            K evit = lists.get(min).iterator().next();
            delete(evit);
        }

        storage.put(key, value);
        counters.put(key, 1L);
        min = 1L;
        lists.get(1L).add(key);
    }

    @Override
    public void delete(K key) {
        lists.get(min).remove(key);
        storage.remove(key);
        counters.remove(key);
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
        lists.clear();
        counters.clear();
    }
}
