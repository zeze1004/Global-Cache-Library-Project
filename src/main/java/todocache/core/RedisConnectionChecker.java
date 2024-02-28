package todocache.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class RedisConnectionChecker {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @PostConstruct
    public void checkRedisConnection() {
        String response = redisTemplate.getConnectionFactory().getConnection().ping();
        System.out.println("Redis server response: " + response);
    }
}
