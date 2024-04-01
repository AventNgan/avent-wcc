package com.avent.location.util;

import com.avent.location.util.DistanceCalculationUtil;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DistanceCalculationUtilTest {

    @Test
    public void shouldCalculateDistanceMeter() {
        double latitude1 = 1;
        double longitude1 = 1;
        double latitude2 = 0;
        double longitude2 = 0;

        double result = DistanceCalculationUtil.calculateDistanceMeter(latitude1, longitude1, latitude2, longitude2);

        assertEquals(BigDecimal.valueOf(157249.56), BigDecimal.valueOf(result).setScale(2, BigDecimal.ROUND_HALF_UP));
    }

    @Test
    public void shouldCalculateDistanceMeterWhenCoordinatesAreSame() {
        double latitude1 = 51.5074;
        double longitude1 = -0.1278;
        double latitude2 = 51.5074;
        double longitude2 = -0.1278;

        double result = DistanceCalculationUtil.calculateDistanceMeter(latitude1, longitude1, latitude2, longitude2);

        assertEquals(0.0, result);
    }

    @Test
    public void shouldCalculateHaversine() {
        double deg1 = Math.toRadians(1);
        double deg2 = Math.toRadians(0);

        double result = DistanceCalculationUtil.haversine(deg1, deg2);

        assertEquals(BigDecimal.valueOf(0.00007615), BigDecimal.valueOf(result).setScale(8, BigDecimal.ROUND_HALF_UP));
    }

    @Test
    public void shouldCalculateHaversineWhenDegreesAreSame() {
        double deg1 = Math.toRadians(1.001);
        double deg2 = Math.toRadians(1.001);

        double result = DistanceCalculationUtil.haversine(deg1, deg2);

        assertEquals(0.0, result);
    }

    @Test
    public void shouldCalculateSquare() {
        double x = 5.0;

        double result = DistanceCalculationUtil.square(x);

        assertEquals(25.0, result);
    }
}
