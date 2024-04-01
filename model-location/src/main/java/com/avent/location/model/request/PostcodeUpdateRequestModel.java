package com.avent.location.model.request;

import com.avent.base.model.AuditTrailRecord;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PostcodeUpdateRequestModel implements AuditTrailRecord {

    @NotNull
    private String postcode;

    @NotNull
    private Double longitude;

    @NotNull
    private Double latitude;

}
