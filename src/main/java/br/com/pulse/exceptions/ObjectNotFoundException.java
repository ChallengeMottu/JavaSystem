package br.com.pulse.exceptions;

import java.util.UUID;

public class ObjectNotFoundException extends RuntimeException {

    public ObjectNotFoundException(String entity, Long id) {

        super(String.format("%s com ID %d não encontrado.", entity, id));
    }

    public ObjectNotFoundException(String entity, UUID codigo) {
        super(String.format("%s com código %s não encontrado.", entity, codigo));
    }
}
