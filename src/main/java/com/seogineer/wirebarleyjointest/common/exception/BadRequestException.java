package com.seogineer.wirebarleyjointest.common.exception;

public class BadRequestException extends RuntimeException {
    private static final String MESSAGE = "THE REQUEST IS NOT VALID";

    public BadRequestException() {
        super(MESSAGE);
    }
}
