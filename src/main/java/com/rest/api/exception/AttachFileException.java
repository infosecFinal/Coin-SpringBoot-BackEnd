package com.rest.api.exception;


@SuppressWarnings("serial")
public class AttachFileException extends RuntimeException{
    public AttachFileException(String msg) {
        super(msg);
    }

    public AttachFileException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
