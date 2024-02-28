package todocache.todo.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class TodoItem {

    private String todoId;
    private boolean completed;
}
