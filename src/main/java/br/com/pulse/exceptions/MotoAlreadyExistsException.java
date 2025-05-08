package br.com.pulse.exceptions;

public class MotoAlreadyExistsException extends RuntimeException {
    public MotoAlreadyExistsException(String message) {
        super(message);
    }
}
