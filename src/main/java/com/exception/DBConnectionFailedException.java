package com.exception;

public class DBConnectionFailedException extends RuntimeException{

	private static final long serialVersionUID = 3980520392102269949L;
	
	public DBConnectionFailedException(){
		super();
	}
	
	public DBConnectionFailedException(String message){
		super(message);
	}
	
	public DBConnectionFailedException(String message, Throwable cause){
		super(message, cause);
	}
	
	public DBConnectionFailedException(Throwable cause){
		super(cause);
	}
	
}
