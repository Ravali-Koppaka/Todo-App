package com.exception;

public class ResultSetException extends RuntimeException{

	private static final long serialVersionUID = 8762251624419758394L;
	
	public ResultSetException(){
		super();
	}
	
	public ResultSetException(String message){
		super(message);
	}
	
	public ResultSetException(String message, Throwable cause){
		super(message, cause);
	}
	
	public ResultSetException(Throwable cause){
		super(cause);
	}

}
