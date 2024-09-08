package org.letscareer.letscareer.global.common.utils.redis.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class RedisUtils {
    private final RedisTemplate<String, String> redisTemplate;

    public void setWithExpire(String key, String value, int expire, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, expire, timeUnit);
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }
}
