package com.exception;

public class TodoStoreException extends RuntimeException{

	private static final long serialVersionUID = -322929545835366021L;
	
	public TodoStoreException(){
		super();
	}
	
	public TodoStoreException(String message){
		super(message);
	}
	
	public TodoStoreException(String message, Throwable cause){
		super(message, cause);
	}
	
	public TodoStoreException(Throwable cause){
		super(cause);
	}
	
}
