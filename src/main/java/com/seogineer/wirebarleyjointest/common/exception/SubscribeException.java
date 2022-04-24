package com.seogineer.wirebarleyjointest.common.exception;

public class SubscribeException extends RuntimeException {
    private static final String MESSAGE = "EXTERNAL API NO RESPONSE";

    public SubscribeException() {
        super(MESSAGE);
    }
}
