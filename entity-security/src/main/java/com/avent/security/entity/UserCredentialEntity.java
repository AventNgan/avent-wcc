package com.avent.security.entity;

import com.avent.base.entity.BaseAuditEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER_CREDENTIAL")
@Data
public class UserCredentialEntity extends BaseAuditEntity {

    @Id
    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;
}
