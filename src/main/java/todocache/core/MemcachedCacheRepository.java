package todocache.core;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import net.rubyeye.xmemcached.MemcachedClient;

import todocache.todo.domain.TodoItem;

@Profile("memcached")
@Component
public class MemcachedCacheRepository implements CacheRepository {

    private final MemcachedClient memcachedClient;

    @Autowired
    public MemcachedCacheRepository(MemcachedClient memcachedClient) {
        this.memcachedClient = memcachedClient;
    }

    @Override
    public TodoItem findById(String id) {
        try {
            return memcachedClient.get(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new NullPointerException("Cache is null");
    }

    @Override
    public void save(TodoItem todoItem) {
        try {
            memcachedClient.set(todoItem.getTodoId(), 3600, todoItem); // 예를 들어 1시간 동안 캐시
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(String id) {
        try {
            memcachedClient.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
