package com.ioiox.dei.rest.api.clients.exception;

public class DeiRestApiUnavailableException extends RuntimeException {

    public DeiRestApiUnavailableException(String message) {
        super(message);
    }

    public DeiRestApiUnavailableException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeiRestApiUnavailableException(Throwable cause) {
        super(cause);
    }
}
