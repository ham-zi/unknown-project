package com.one.unknown.exception;

public class DuplicateUserIdException extends RuntimeException {
	public DuplicateUserIdException(String message) {
		super(message);
	}

}
