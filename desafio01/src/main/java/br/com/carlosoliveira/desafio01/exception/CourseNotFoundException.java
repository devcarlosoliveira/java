package br.com.carlosoliveira.desafio01.exception;

public class CourseNotFoundException extends RuntimeException {
	public CourseNotFoundException() {
		super("Course not found");
	}

	public CourseNotFoundException(String message) {
		super(message);
	}
}
