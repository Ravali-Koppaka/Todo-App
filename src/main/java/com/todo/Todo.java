package com.todo;

import java.util.Date;

public class Todo { 
	
	private int id;
	private String name;
	private String description;
	private Date dueDate;
	private Status status;
	
	public Todo(int id, String name, String description, Date dueDate, Status status){
		this.id = id;
		this.name = name;
		this.description = description;
		this.dueDate = dueDate;
		this.status = status;
	}
	
	public int getId(){
		return id;
	}
	
	public void setId(int id){
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}
	
	public Date getDueDate() {
		return dueDate;
	}
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
