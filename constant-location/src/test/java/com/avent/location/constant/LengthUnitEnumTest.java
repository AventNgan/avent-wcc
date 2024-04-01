package com.avent.location.constant;

import com.avent.exception.InvalidEnumException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LengthUnitEnumTest {

    @Test
    public void shouldReturnEnumWhenNameIsValid() throws InvalidEnumException {
        String name = "km";
        LengthUnitEnum expectedUnit = LengthUnitEnum.km;

        LengthUnitEnum actualUnit = LengthUnitEnum.fromName(name);

        assertEquals(expectedUnit, actualUnit);
    }

    @Test
    public void shouldThrowInvalidEnumExceptionWhenNameIsInvalid() {
        String name = "invalid";

        assertThrows(InvalidEnumException.class, () -> LengthUnitEnum.fromName(name));
    }
}
