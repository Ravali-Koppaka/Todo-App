package com.exception;

public class DriverNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = -6666152565944377233L;

	public DriverNotFoundException(){
		super();
	}
	
	public DriverNotFoundException(String message){
		super(message);
	}
	
	public DriverNotFoundException(String message, Throwable cause){
		super(message, cause);
	}
	
	public DriverNotFoundException(Throwable cause){
		super(cause);
	}
}
