package br.com.carlosoliveira.desafio01.exception;

public class CourseAlreadyExistsException extends RuntimeException {

	public CourseAlreadyExistsException() {
		super("Course already exists");
	}

	public CourseAlreadyExistsException(String message) {
		super(message);
	}
}
