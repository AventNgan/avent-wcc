package com.avent.audittrail.repository;

import com.avent.base.repository.BaseJpaRepository;
import com.avent.base.entity.AuditTrailEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditTrailDao extends BaseJpaRepository<AuditTrailEntity, Long> {

}
