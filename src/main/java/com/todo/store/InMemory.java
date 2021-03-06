package com.todo.store;

import java.util.ArrayList;
import java.util.List;

import com.todo.Status;
import com.todo.Todo;

public class InMemory implements TodoStore{
	private List<Todo> todoList = new ArrayList<Todo>();

	public Todo create(Todo todo) {
		todoList.add(todo);
		return todo;
	}

	public Todo retrieve(int id) {
		for (Todo todo : todoList) {
			if (todo.getId() == id) {
				return todo;
			}
		}
		throw new NullPointerException();
	}

	public Todo update(int id, Status status) {
		Todo todo = retrieve(id);
		todo.setStatus(status);
		return todo;
	}

	public Todo delete(int id) {
		Todo todo = retrieve(id);
		todoList.remove(todo);
		return todo;
	}
	
	public List<Todo> getList(){
		return todoList;
	}
}
