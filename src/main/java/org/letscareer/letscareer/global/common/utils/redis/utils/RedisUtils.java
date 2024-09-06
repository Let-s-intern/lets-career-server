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

    public void setRefreshToken(Long id, String token, int expirationTime) {
        redisTemplate.opsForValue().set(id.toString(), token, expirationTime, TimeUnit.SECONDS);
    }

    public String getRefreshToken(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void setLogoutAccessToken(String accessToken, long expirationTime) {
        redisTemplate.opsForValue().set(accessToken, "logout", Duration.ofMillis(expirationTime));
    }

    public String getLogoutAccessToken(String accessToken) {
        return redisTemplate.opsForValue().get(accessToken);
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }
}
