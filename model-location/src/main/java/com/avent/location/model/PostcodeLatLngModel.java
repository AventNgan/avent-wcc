package com.avent.location.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostcodeLatLngModel {
    private String postcode;
    private Double latitude;
    private Double longitude;
}
