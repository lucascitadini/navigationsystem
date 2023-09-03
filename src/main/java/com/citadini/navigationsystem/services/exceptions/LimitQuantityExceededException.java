package com.citadini.navigationsystem.services.exceptions;

public class LimitQuantityExceededException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public LimitQuantityExceededException(String msg) {
		super(msg);
	}
	
	public LimitQuantityExceededException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
