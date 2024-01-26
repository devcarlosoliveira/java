package br.com.carlos_oliveira.gestao_vagas.exceptions;

public class UserNotFoundException extends RuntimeException {

	public UserNotFoundException() {
		super("User not found");
	}

	public UserNotFoundException(String message) {
		super(message);
	}
}
