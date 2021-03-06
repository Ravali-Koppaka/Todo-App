package com.todo.app;

import java.util.List;

import com.todo.Status;
import com.todo.Todo;
import com.todo.store.InMemory;
import com.todo.store.TodoStore;

public class TodoApp {
	private TodoStore todoStore = new InMemory();
	private int todoId = 0;
	
	public Todo addTodo(Todo todo) {
		todo.setId(todoId++);
		todo.setStatus(Status.PROGRESS);
		return todoStore.create(todo);
	}
	
	public Todo updateStatusCompleted(int id) {
		return todoStore.update(id, Status.COMPLETED);
	}
	
	public Todo deleteTodo(int id) {
		return todoStore.delete(id);
	}
	
	public List<Todo> listTodos() {
		return todoStore.getList();
	}
}
