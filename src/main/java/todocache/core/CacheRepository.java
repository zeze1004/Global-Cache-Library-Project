package todocache.core;

import java.util.Optional;

import todocache.todo.domain.TodoItem;

public interface CacheRepository {
    TodoItem findById(String id);
    void save(TodoItem todoItem);
    void deleteById(String id);
}
