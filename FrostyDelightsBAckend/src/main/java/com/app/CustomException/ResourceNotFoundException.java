package com.app.CustomException;

@SuppressWarnings("serial")
public class ResourceNotFoundException extends RuntimeException {

	public ResourceNotFoundException(String mesg) {
		super(mesg);
}
}