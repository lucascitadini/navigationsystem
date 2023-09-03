package com.citadini.navigationsystem.services.exceptions;

public class ObjectNotFoundExcpetion extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ObjectNotFoundExcpetion() {
		super();
	}
	
	public ObjectNotFoundExcpetion(String msg) {
		super(msg);
	}
}
