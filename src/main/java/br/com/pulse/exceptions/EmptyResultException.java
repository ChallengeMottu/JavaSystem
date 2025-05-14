package br.com.pulse.exceptions;

public class EmptyResultException extends RuntimeException {
    public EmptyResultException(String message) {
        super(message);
    }
}
