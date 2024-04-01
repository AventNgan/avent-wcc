package com.avent.base.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ObjectUtilTest {

    @Test
    public void shouldReturnTrueWhenAnyObjectIsNull() {
        assertTrue(ObjectUtil.isAnyNull(null, "test", 123));
    }

    @Test
    public void shouldReturnFalseWhenNoObjectIsNull() {
        assertFalse(ObjectUtil.isAnyNull("test", 123, new Object()));
    }

    @Test
    public void shouldReturnTrueWhenAllObjectsAreNull() {
        assertTrue(ObjectUtil.isAnyNull(null, null, null));
    }

    @Test
    public void shouldReturnFalseWhenEmptyArgumentList() {
        assertFalse(ObjectUtil.isAnyNull());
    }
}
