package com.avent.location.entity;

import com.avent.base.entity.BaseAuditEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "POSTCODE_REQUEST_LOG")
@Data
@NoArgsConstructor
public class PostcodeRequestLogEntity extends BaseAuditEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "REQUEST_ID")
    private String requestId;

    @Column(name = "POSTCODE")
    private String postcode;

    @Column(name = "LATITUDE")
    private Double latitude;

    @Column(name = "LONGITUDE")
    private Double longitude;

    public PostcodeRequestLogEntity(String requestId, String postcode, Double latitude, Double longitude) {
        this.requestId = requestId;
        this.postcode = postcode;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
