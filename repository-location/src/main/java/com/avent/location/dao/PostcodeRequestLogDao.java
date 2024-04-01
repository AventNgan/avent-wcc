package com.avent.location.dao;

import com.avent.base.repository.BaseJpaRepository;
import com.avent.location.entity.PostcodeRequestLogEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface PostcodeRequestLogDao extends BaseJpaRepository<PostcodeRequestLogEntity, Long> {
}
