package org.letscareer.letscareer.global.common.utils.redis.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class RedisUtils {
    private final RedisTemplate<String, String> redisTemplate;
    private final ObjectMapper objectMapper;

    public void setWithExpire(String key, String value, int expire, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, expire, timeUnit);
    }

    public <T> void setObjectWithExpire(String key, T data, int expire, TimeUnit timeUnit) {
        try {
            redisTemplate.opsForValue().set(key, objectMapper.writeValueAsString(data), expire, timeUnit);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> Optional<T> getData(String key, Class<T> classType) {
        String value = redisTemplate.opsForValue().get(key);
        if(value == null) return Optional.empty();
        try {
            return Optional.of(objectMapper.readValue(value, classType));
        } catch(Exception e){
            return Optional.empty();
        }
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }
}
