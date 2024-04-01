package com.avent.security.dao;

import com.avent.base.repository.BaseJpaRepository;
import com.avent.security.entity.UserCredentialEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCredentialDao extends BaseJpaRepository<UserCredentialEntity, String> {

    UserCredentialEntity findByUsername(String username);
}
