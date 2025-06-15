package com.casestudy.analytics.repository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
@AllArgsConstructor
@Slf4j
public class RedisRepository <K, V> {

    private final RedisTemplate<K,V> redisTemplate;

    //pick from configuration in prod environment
    private final Integer REDIS_TIMEOUT = 1;
    private final TimeUnit TIME_UNIT = TimeUnit.HOURS;

    /**
     * Saves the given key-value pair in Redis with an expiration time.
     * If the key already exists, it will not overwrite the existing value.
     *
     * @param key   the key to save
     * @param value the value to save
     */
    public void save(K key, V value) {
        log.debug("Saving value in Redis for key: {}", key);
        boolean saved = redisTemplate.opsForValue().setIfAbsent(key, value, REDIS_TIMEOUT, TIME_UNIT);
        if (saved) {
            log.info("Successfully saved value in Redis for key: {} with timeout: {} {}", 
                    key, REDIS_TIMEOUT, TIME_UNIT);
        } else {
            log.debug("Value already exists in Redis for key: {}", key);
        }
    }

    /**
     * Retrieves the value associated with the given key from Redis.
     *
     * @param key the key to look up
     * @return the value associated with the key, or null if not found
     */
    public V get(K key) {
        log.debug("Retrieving value from Redis for key: {}", key);
        V value = redisTemplate.opsForValue().get(key);
        if (value == null) {
            log.debug("No value found in Redis for key: {}", key);
        } else {
            log.debug("Successfully retrieved value from Redis for key: {}", key);
        }
        return value;
    }

}
