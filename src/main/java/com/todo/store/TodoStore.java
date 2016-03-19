package com.todo.store;

import java.util.List;

import com.todo.Status;
import com.todo.Todo;

public interface TodoStore {
	Todo create(Todo todo);
	Todo retrieve(int id);
	Todo update(int id, Status status);
	Todo delete(int id);
	List<Todo> getList();
}
