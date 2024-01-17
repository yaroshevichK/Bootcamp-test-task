package it.bootcamp.exceptions;

public class NotCorrectPageException extends RuntimeException {
    public NotCorrectPageException(String message) {
        super(message);
    }
}
