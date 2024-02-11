package br.com.carlosoliveira.desafio01.constraint;

import java.text.MessageFormat;

public enum CursoStatusEnum {
    ATIVO,
    DESATIVADO;

    public boolean getDescription() {
        switch (this) {
            case ATIVO:
                return Boolean.TRUE;
            case DESATIVADO:
                return Boolean.FALSE;
        }
        throw new IllegalArgumentException(MessageFormat.format("Status de curso não existe: {0}", this));
    }

    public boolean getStatus() {
        return this == CursoStatusEnum.ATIVO;
    }
}
