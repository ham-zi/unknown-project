package com.one.unknown.exception;

public class NotMatchesPasswordException extends RuntimeException{
	public NotMatchesPasswordException(String message) {
		super(message);
	}
}
