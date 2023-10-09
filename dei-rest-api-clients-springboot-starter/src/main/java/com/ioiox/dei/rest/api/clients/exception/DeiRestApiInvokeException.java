package com.ioiox.dei.rest.api.clients.exception;

public class DeiRestApiInvokeException extends RuntimeException {

    private Integer code;

    private String traceId;

    public DeiRestApiInvokeException(final String message, final Integer code, final String traceId) {
        this(message);
        this.code = code;
        this.traceId = traceId;
    }

    public DeiRestApiInvokeException(final String message) {
        super(message);
    }

    public DeiRestApiInvokeException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public DeiRestApiInvokeException(final Throwable cause) {
        super(cause);
    }

    public Integer getCode() {
        return code;
    }

    public String getTraceId() {
        return traceId;
    }
}
