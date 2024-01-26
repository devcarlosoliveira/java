package br.com.carlos_oliveira.gestao_vagas.exceptions;

public class CompanyNotFoundException extends RuntimeException {

    public CompanyNotFoundException() {
        super("Company not found");
    }

    public CompanyNotFoundException(String message) {
        super(message);
    }
}
