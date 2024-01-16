package br.com.carlos_oliveira.gestao_vagas.exceptions;

public class UsernameNotFoundException extends RuntimeException {

	public UsernameNotFoundException() {
		super("Username not found");
	}

	public UsernameNotFoundException(String message) {
		super(message);
	}

}
