package todocache.todo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import todocache.todo.domain.TodoItem;
import todocache.todo.repository.ItemRepository;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository repository;

    public TodoItem findById(String id) {
        return repository.findById(id);
    }

    public void save(TodoItem todoItem) {
        repository.save(todoItem);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
