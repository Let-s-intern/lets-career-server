package org.letscareer.letscareer.global.config;

//import org.letscareer.letscareer.global.common.utils.redis.listener.EventListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
//import org.springframework.data.redis.core.RedisKeyValueAdapter;
import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.listener.PatternTopic;
//import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableRedisRepositories
//@EnableRedisRepositories(
//        enableKeyspaceEvents = RedisKeyValueAdapter.EnableKeyspaceEvents.ON_STARTUP,
//        keyspaceNotificationsConfigParameter = ""
//)
public class RedisConfig {
//    private final String EXPIRED_EVENT_PATTERN = "__keyevent@*__:expired";
//    private final String EXPIRED_SPACE_PATTERN = "__keyspace@*__:expired";
//    private final String SET_EVENT_PATTERN = "__keyevent@*__:set";

    @Value("${spring.data.redis.host}")
    private String host;
    @Value("${spring.data.redis.port}")
    private int port;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory(host, port);
    }

    @Bean
    public RedisTemplate<?, ?> redisTemplate() {
        RedisTemplate<?, ?> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        return redisTemplate;
    }

//    @Bean
//    public RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory redisConnectionFactory,
//                                                                       EventListener eventListener) {
//        RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
//        redisMessageListenerContainer.setConnectionFactory(redisConnectionFactory);
//        redisMessageListenerContainer.addMessageListener(eventListener, new PatternTopic(SET_EVENT_PATTERN));
//        redisMessageListenerContainer.addMessageListener(eventListener, new PatternTopic(EXPIRED_EVENT_PATTERN));
//        redisMessageListenerContainer.addMessageListener(eventListener, new PatternTopic(EXPIRED_SPACE_PATTERN));
//        return redisMessageListenerContainer;
//    }
}
