package todocache.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;

import todocache.core.MemcachedCacheRepository;
import todocache.todo.repository.ItemRepository;
import todocache.core.CacheRepository;

@Configuration
public class CacheResourceConfig {

    @Value("${memcached.cache.server}")
    private String memcachedServer;

    @Bean
    @ConditionalOnProperty(name = "cache.type", havingValue = "memcached")
    public MemcachedClient memcachedClient() throws Exception {
        // Memcached 서버 주소를 기반으로 MemcachedClient 인스턴스 생성
        XMemcachedClientBuilder builder = new XMemcachedClientBuilder(memcachedServer);
        return builder.build();
    }

    @Bean
    @ConditionalOnProperty(name = "cache.type", havingValue = "memcached")
    public CacheRepository memcachedTodoCacheManager(MemcachedClient memcachedClient) {
        // 생성된 MemcachedClient 인스턴스를 MemcachedTodoCacheManager에 주입
        return new MemcachedCacheRepository(memcachedClient);
    }

    @Bean
    @ConditionalOnProperty(name = "cache.type", havingValue = "redis")
    public CacheRepository redisTodoCacheManager(ItemRepository itemRepository) {
        return itemRepository;
    }
}
