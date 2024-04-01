package com.avent.location.constant;

import com.avent.exception.InvalidEnumException;

public enum LengthUnitEnum {
    km,
    m,
    mile,
    foot;

    public static LengthUnitEnum fromName(String name) throws InvalidEnumException {
        for (LengthUnitEnum unit : LengthUnitEnum.values()) {
            if (unit.name().equals(name)) {
                return unit;
            }
        }
        throw new InvalidEnumException(LengthUnitEnum.class, name);
    }
}
