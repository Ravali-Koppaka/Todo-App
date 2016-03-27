package com.todo.store;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.exception.DBConnectionFailedException;
import com.exception.DriverNotFoundException;
import com.exception.InvalidParametersException;
import com.exception.ResultSetException;
import com.exception.TodoStoreException;
import com.todo.Status;
import com.todo.Todo;

public class TodoDatabaseStore implements TodoStore {
	private final String insertQuery   = "insert into todo values (?, ?, ?, ?, ?) ;";
	private final String updateQuery   = "update todo set status= ? where id= ? ;";
	private final String deleteQuery   = "delete from todo where id= ? ;";
	private final String selectQuery   = "select * from todo ;";
	private final String retrieveQuery = "select * from todo where id= ? ;";
	
	private Connection connection;
	private int id;
	
	private void openConnection(String dbName, String user, String password) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/"+dbName, user, password);
		} catch (ClassNotFoundException e) {
			throw new DriverNotFoundException("JDBC Driver class not found", e);
		} catch (SQLException e) {
			throw new DBConnectionFailedException("Connection to DB failed", e);
		}
	}

	public Todo create(Todo todo) {
		todo.setId(id++);
		PreparedStatement insertStatement = null;
		try {
			openConnection("db", "ravali", "ravali");
			insertStatement = connection.prepareStatement(insertQuery);
			setParameters(insertStatement, todo);
			insertStatement.executeUpdate();
		} catch (SQLException e) {
			throw new TodoStoreException(e);
		} finally {
			cleanUp(insertStatement);
		}
		return todo;
	}

	public Todo retrieve(int id) {
		Todo todo = null;
		PreparedStatement retrieveStatement = null;
		try {
			openConnection("db", "ravali", "ravali");
			retrieveStatement = connection.prepareStatement(retrieveQuery);
			retrieveStatement.setInt(1, id);
			ResultSet resultSet = null;
			try{
				resultSet = retrieveStatement.executeQuery();
				if ( resultSet.next() ) {
					todo = getTodo(resultSet);
				}
			} finally{
				resultSet.close();
			}
		} catch (SQLException e) {
			throw new TodoStoreException(e);
		} finally {
			cleanUp(retrieveStatement); 
		}
		return todo;
	}

	public Todo update(int id, Status status) {
		PreparedStatement updateStatement = null;
		try {
			openConnection("db", "ravali", "ravali");
			updateStatement = connection.prepareStatement(updateQuery);
			updateStatement.setString(1, status.toString());
			updateStatement.setInt(2, id);
			updateStatement.executeUpdate();
		} catch (SQLException e) {
			throw new TodoStoreException(e);
		} finally{
			cleanUp(updateStatement);
		}
		return retrieve(id);
	}

	public Todo delete(int id) {
		Todo todo = retrieve(id);
		PreparedStatement deleteStatement = null;
		try {
			openConnection("db", "ravali", "ravali");
			deleteStatement = connection.prepareStatement(deleteQuery);
			deleteStatement.setInt(1, id);
			deleteStatement.executeUpdate();
		} catch (SQLException e) {
			throw new TodoStoreException(e);
		} finally{
			cleanUp(deleteStatement);
		}
		return todo;
	}

	public List<Todo> getList() {
		List<Todo> todoList = new ArrayList<Todo>();
		Statement statement = null;
		try {
			openConnection("db", "ravali", "ravali");
			statement = connection.createStatement();
			ResultSet resultSet = null;
			try{
				resultSet = statement.executeQuery(selectQuery);
				while ( resultSet.next() ) {
					Todo todo = getTodo(resultSet);
					todoList.add(todo);
				}
				initializeId(resultSet);
			} finally {
				resultSet.close();
			}
		} catch (SQLException e) {
			throw new TodoStoreException(e);
		} finally { 
			cleanUp(statement);
		}
		return todoList;
	}
	
	private void setParameters(PreparedStatement preparedStatement, Todo todo){
		try{
			preparedStatement.setInt(1, todo.getId());
			preparedStatement.setString(2, todo.getName());
			preparedStatement.setString(3, todo.getDescription());
			preparedStatement.setDate(4, new Date(todo.getDueDate().getTime()));
			preparedStatement.setString(5, todo.getStatus().name());
		} catch(SQLException e){
			throw new InvalidParametersException("PreparedStatement: Invalid parameters", e);	
		}
	}
	
	private Todo getTodo(ResultSet resultSet) {
		try {
			return   new Todo(resultSet.getInt("id"),
			         resultSet.getString("name"), 
			         resultSet.getString("description"), 
			         new java.util.Date(resultSet.getDate("dueDate").getTime()),
			         Status.valueOf(resultSet.getString("status")));
		} catch (SQLException e) {
			throw new ResultSetException("ResultSet column name invalid", e);
		}
	}

	private void initializeId(ResultSet resultSet) {
		try {
			if ( resultSet.last() ) {
				id = resultSet.getInt("id") + 1;
			} else {
				id = 0;
			}
		} catch (SQLException e) {
			throw new ResultSetException("ResultSet column access failed", e);
		} 
	}
	
	private void cleanUp(Statement statement){
		try {
			statement.close();
			connection.close();
		} catch(SQLException e) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Database access failed", e);
		}
	}
}
