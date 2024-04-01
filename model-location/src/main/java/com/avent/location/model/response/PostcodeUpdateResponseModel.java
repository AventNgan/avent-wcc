package com.avent.location.model.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostcodeUpdateResponseModel {

    private String postcode;
    private String longitude;
    private String latitude;
    private LocalDateTime updatedDate;

}
