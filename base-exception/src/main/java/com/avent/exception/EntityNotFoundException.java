package com.avent.exception;

import lombok.Getter;

@Getter
public class EntityNotFoundException extends Exception{
    public <T> EntityNotFoundException(Class<T> clazz, String id) {
        super(String.format("Entity not found: %s - %s", clazz.getSimpleName(), id));
    }
}
