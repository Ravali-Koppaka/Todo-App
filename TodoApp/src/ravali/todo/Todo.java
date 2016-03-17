package ravali.todo;

import java.util.Date;

//import com.google.gson.annotations.Expose;

public class Todo { 
	
	private int id;
	private String name;
	private String description;
	private Date dueDate;
	private Status status;
	
	public Todo(String name, String description, Date dueDate){
		this.name = name;
		this.description = description;
		this.dueDate = dueDate;
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
