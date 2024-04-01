package com.avent.location.controller;

import com.avent.base.model.response.ResponseModel;
import com.avent.exception.EntityNotFoundException;
import com.avent.exception.InvalidEnumException;
import com.avent.exception.InvalidRequestException;
import com.avent.location.model.response.DistanceCalculationResponseModel;
import com.avent.location.service.DistanceCalculationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/distance")
@RequiredArgsConstructor
public class DistanceController {

    private final DistanceCalculationService distanceCalculationService;

    @GetMapping("/postcode")
    public ResponseModel<DistanceCalculationResponseModel> getDistanceBetweenPostcodes(
            @RequestParam("postcode1") String postcode1,
            @RequestParam("postcode2") String postcode2,
            @RequestParam(value = "unit", defaultValue = "km") String unit
    ) throws InvalidEnumException, EntityNotFoundException, InvalidRequestException {
        return ResponseModel.success(distanceCalculationService.calculateDistanceAndRetrieveInfoByPostcode(postcode1, postcode2, unit));
    }

}
