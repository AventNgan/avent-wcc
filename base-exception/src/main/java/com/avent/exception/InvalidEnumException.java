package com.avent.exception;

import lombok.Getter;

@Getter
public class InvalidEnumException extends Exception{

    public <T> InvalidEnumException(Class<T> enumName, String value) {
        super(String.format("Invalid Enum: %s - %s", enumName.getSimpleName(), value));
    }
}
