package com.BGL.test.exception;

public class EntrytransactionNotFoundException extends RuntimeException{
    public EntrytransactionNotFoundException(String message) {
        super(message);
    }

    public EntrytransactionNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}