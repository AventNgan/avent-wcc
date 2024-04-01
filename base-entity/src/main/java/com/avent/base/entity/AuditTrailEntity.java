package com.avent.base.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "audit_trail")
@NoArgsConstructor
@Data
public class AuditTrailEntity extends BaseAuditEntity{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "details")
    private String details;

    @Column(name = "username")
    private String username;

    public AuditTrailEntity(String details, String username) {
        this.details = details;
        this.username = username;
    }
}
