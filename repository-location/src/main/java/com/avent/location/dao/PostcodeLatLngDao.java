package com.avent.location.dao;

import com.avent.base.repository.BaseJpaRepository;
import com.avent.location.entity.PostcodeLatLngEntity;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface PostcodeLatLngDao extends BaseJpaRepository<PostcodeLatLngEntity, Long> {

    List<PostcodeLatLngEntity> findByPostcodeIn(Collection<String> postcodes);

    PostcodeLatLngEntity findByPostcode(String postcode);


}
