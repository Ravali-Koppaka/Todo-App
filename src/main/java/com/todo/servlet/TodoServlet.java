package com.todo.servlet;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.todo.Todo;
import com.todo.app.TodoApp;

public class TodoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Gson gson = new Gson();
	private TodoApp todoApp = new TodoApp();

	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		List<Todo> todoList = todoApp.listTodos();
		sendAsJson(todoList, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		Todo todo = gson.fromJson(request.getReader(), Todo.class);
		sendAsJson(todoApp.addTodo(todo), response);
	}

	public void doPut(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getPathInfo().substring(1));
		sendAsJson(todoApp.updateStatusCompleted(id), response);
	}
	
	public void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getPathInfo().substring(1));
		sendAsJson(todoApp.deleteTodo(id), response);
	}
	
	private void sendAsJson(Object object, HttpServletResponse response) 
			throws IOException {
		String json = gson.toJson(object);
		response.setContentType("application/json");
		response.getWriter().write(json);
	}
}
