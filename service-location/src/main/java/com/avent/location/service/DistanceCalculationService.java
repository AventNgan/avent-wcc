package com.avent.location.service;


import com.avent.exception.EntityNotFoundException;
import com.avent.exception.InvalidEnumException;
import com.avent.exception.InvalidRequestException;
import com.avent.location.model.base.CoordinatesModel;
import com.avent.location.model.response.DistanceCalculationResponseModel;

import java.math.BigDecimal;

public interface DistanceCalculationService {

    DistanceCalculationResponseModel calculateDistanceAndRetrieveInfoByPostcode(String location1, String location2, String unit) throws InvalidEnumException, EntityNotFoundException, InvalidRequestException;

    BigDecimal calculateDistanceByPostCode(String location1, String location2, String unit) throws InvalidEnumException, InvalidRequestException;

    BigDecimal calculateDistanceByCoordinates(Double lat1, Double lng1, Double lat2, Double lng2, String unit) throws InvalidEnumException, InvalidRequestException;
}
