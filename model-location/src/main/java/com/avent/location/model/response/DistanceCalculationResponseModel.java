package com.avent.location.model.response;

import com.avent.location.model.base.PostcodeLocationModel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class DistanceCalculationResponseModel {

    private PostcodeLocationModel origin;
    private PostcodeLocationModel destination;
    private BigDecimal distance;
    private String unit;

}
