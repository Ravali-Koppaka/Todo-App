package com.exception;

public class InvalidParametersException extends RuntimeException{

	private static final long serialVersionUID = 3949325458928625398L;
	
	public InvalidParametersException(){
		super();
	}
	
	public InvalidParametersException(String message){
		super(message);
	}
	
	public InvalidParametersException(String message, Throwable cause){
		super(message, cause);
	}
	
	public InvalidParametersException(Throwable cause){
		super(cause);
	}
}
