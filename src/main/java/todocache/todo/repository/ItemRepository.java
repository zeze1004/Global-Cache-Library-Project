package todocache.todo.repository;

import java.util.Objects;
import java.util.Optional;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import todocache.core.CacheRepository;
import todocache.todo.domain.TodoItem;

@Repository
@RequiredArgsConstructor
public class ItemRepository implements CacheRepository {

    private final RedisTemplate<String, Object> redisTemplate;
    private final HashOperations<String, Boolean, Object> hashOperations;
    private final CacheManager cacheManager;

    @Override
    public TodoItem findById(String id) {
        Cache cache = cacheManager.getCache("todoItem");
        if (cache != null) {
            cache.get(id, TodoItem.class);
            return (TodoItem) Objects.requireNonNull(cache.get(id)).get();
        }
        throw new NullPointerException("Cache is null");
    }

    @Override
    public void save(TodoItem todoItem) {
        Cache cache = cacheManager.getCache("todoItem");
        if (cache != null) {
            cache.put(todoItem.getTodoId(), todoItem);
        }
    }

    @Override
    public void deleteById(String id) {
        String key = id;
        redisTemplate.delete(key);
    }
}
