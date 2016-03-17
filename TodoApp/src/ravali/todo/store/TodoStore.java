package ravali.todo.store;

import java.util.List;

import ravali.todo.Status;
import ravali.todo.Todo;

public interface TodoStore {
	Todo create(Todo todo);
	Todo retrieve(int id);
	Todo update(int id, Status status);
	Todo delete(int id);
	List<Todo> getList();
}
