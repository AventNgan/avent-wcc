package com.avent.location.util;

import com.avent.exception.InvalidEnumException;
import com.avent.location.constant.LengthUnitEnum;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DistanceUnitConversionUtilTest {

    @Test
    public void shouldConvertMeterToKilometer() {
        double meter = 1000.0;
        double expectedKilometer = 1.0;

        double actualKilometer = DistanceUnitConversionUtil.convertMeterToKilometer(meter);

        assertEquals(expectedKilometer, actualKilometer);
    }

    @Test
    public void shouldConvertMeterToMile() {
        double meter = 1609.34;
        double expectedMile = 1.0;

        double actualMile = DistanceUnitConversionUtil.convertMeterToMile(meter);

        assertEquals(expectedMile, actualMile);
    }

    @Test
    public void shouldConvertMeterToFoot() {
        double meter = 1.0;
        double expectedFoot = 3.28084;

        double actualFoot = DistanceUnitConversionUtil.convertMeterToFoot(meter);

        assertEquals(expectedFoot, actualFoot);
    }

    @Test
    public void shouldConvertMeterToSameUnit() throws InvalidEnumException {
        double meter = 1.0;

        Double actualMeter = DistanceUnitConversionUtil.convert(meter, LengthUnitEnum.m);

        assertEquals(meter, actualMeter);
    }

    @Test
    public void shouldConvertMeterToDifferentUnits() throws InvalidEnumException {
        double meter = 1000;
        double expectedKilometer = 1;
        double expectedMile = 0.62;
        double expectedFoot = 3280.84;

        Double actualKilometer = DistanceUnitConversionUtil.convert(meter, LengthUnitEnum.km);
        Double actualMile = DistanceUnitConversionUtil.convert(meter, LengthUnitEnum.mile);
        Double actualFoot = DistanceUnitConversionUtil.convert(meter, LengthUnitEnum.foot);

        assertEquals(BigDecimal.valueOf(expectedKilometer).setScale(2, RoundingMode.HALF_UP), BigDecimal.valueOf(actualKilometer).setScale(2, RoundingMode.HALF_UP));
        assertEquals(BigDecimal.valueOf(expectedMile).setScale(2, RoundingMode.HALF_UP), BigDecimal.valueOf(actualMile).setScale(2, RoundingMode.HALF_UP));
        assertEquals(BigDecimal.valueOf(expectedFoot).setScale(2, RoundingMode.HALF_UP), BigDecimal.valueOf(actualFoot).setScale(2, RoundingMode.HALF_UP));
    }

    @Test
    public void shouldReturnNullWhenUnitIsNotSupported() throws InvalidEnumException {
        double meter = 1.0;

        Double actualValue = DistanceUnitConversionUtil.convert(meter, null);

        assertEquals(null, actualValue);
    }
}
