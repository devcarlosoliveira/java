package br.com.carlosoliveira.desafio01.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException() {
        super("Argument not found");
    }

    public NotFoundException(String message) {
        super(message);
    }
}
