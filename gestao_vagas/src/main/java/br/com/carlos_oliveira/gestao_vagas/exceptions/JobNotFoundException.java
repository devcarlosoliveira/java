package br.com.carlos_oliveira.gestao_vagas.exceptions;

public class JobNotFoundException extends RuntimeException {

	public JobNotFoundException() {
		super("Job not found");
	}

	public JobNotFoundException(String message) {
		super(message);
	}

}
