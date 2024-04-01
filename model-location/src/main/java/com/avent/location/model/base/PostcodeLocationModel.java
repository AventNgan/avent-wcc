package com.avent.location.model.base;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostcodeLocationModel {

    private String postcode;
    private CoordinatesModel coordinates;

}
