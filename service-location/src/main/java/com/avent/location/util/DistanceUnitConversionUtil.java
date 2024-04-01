package com.avent.location.util;

import com.avent.exception.InvalidEnumException;
import com.avent.location.constant.LengthUnitEnum;

public class DistanceUnitConversionUtil {

    public static double convertMeterToKilometer(double meter) {
        return meter / 1000;
    }

    public static double convertMeterToMile(double meter) {
        return meter / 1609.34;
    }

    public static double convertMeterToFoot(double meter) {
        return meter * 3.28084;
    }

    public static Double convert(double distance, LengthUnitEnum unit) throws InvalidEnumException {
        if (unit == null) return null;
        switch (unit) {
            case m:
                return distance;
            case km:
                return convertMeterToKilometer(distance);
            case mile:
                return convertMeterToMile(distance);
            case foot:
                return convertMeterToFoot(distance);
            default:
                return null;
        }

    }


}
