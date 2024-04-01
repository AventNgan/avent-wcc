package com.avent.location.entity;

import com.avent.base.entity.BaseAuditEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "POSTCODE_LAT_LNG")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostcodeLatLngEntity extends BaseAuditEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "POSTCODE")
    private String postcode;

    @Column(name = "LATITUDE")
    private Double latitude;

    @Column(name = "LONGITUDE")
    private Double longitude;


}
