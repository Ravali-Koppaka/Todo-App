package ravali.todo.store;

import java.util.ArrayList;
import java.util.List;

import ravali.todo.Status;
import ravali.todo.Todo;

public class InMemory implements TodoStore{
	private List<Todo> todoList = new ArrayList<Todo>();

	@Override
	public Todo create(Todo todo) {
		todoList.add(todo);
		return todo;
	}

	@Override
	public Todo retrieve(int id) {
		for (Todo todo : todoList) {
			if (todo.getId() == id) {
				return todo;
			}
		}
		throw new NullPointerException();
	}

	@Override
	public Todo update(int id, Status status) {
		Todo todo = retrieve(id);
		todo.setStatus(status);
		return todo;
	}

	@Override
	public Todo delete(int id) {
		Todo todo = retrieve(id);
		todoList.remove(todo);
		return todo;
	}
	
	public List<Todo> getList(){
		return todoList;
	}
}
