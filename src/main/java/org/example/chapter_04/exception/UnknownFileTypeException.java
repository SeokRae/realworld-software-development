package org.example.chapter_04.exception;

public class UnknownFileTypeException extends RuntimeException {
	public UnknownFileTypeException(final String message) {
		super(message);
	}
}
