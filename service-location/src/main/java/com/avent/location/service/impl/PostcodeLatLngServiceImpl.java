package com.avent.location.service.impl;

import com.avent.base.aspect.annotation.AuditTrail;
import com.avent.exception.EntityNotFoundException;
import com.avent.location.dao.PostcodeLatLngDao;
import com.avent.location.entity.PostcodeLatLngEntity;
import com.avent.location.mapper.PostcodeLatLngMapper;
import com.avent.location.model.PostcodeLatLngModel;
import com.avent.location.model.request.PostcodeUpdateRequestModel;
import com.avent.location.model.response.PostcodeUpdateResponseModel;
import com.avent.location.service.PostcodeLatLngService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostcodeLatLngServiceImpl implements PostcodeLatLngService {

    private final PostcodeLatLngDao postcodeLatLngDao;
    private final PostcodeLatLngMapper postcodeLatLngMapper;

    @Override
    public List<PostcodeLatLngModel> findByPostcodeIn(List<String> postcodes) {
        List<PostcodeLatLngEntity> foundEntities = postcodeLatLngDao.findByPostcodeIn(postcodes);
        return postcodeLatLngMapper.toModels(foundEntities);
    }

    @Override
    @Transactional(readOnly = true)
    public PostcodeLatLngModel findByPostcode(String postcode) throws EntityNotFoundException {
        PostcodeLatLngEntity foundEntity = postcodeLatLngDao.findByPostcode(postcode);
        if (foundEntity == null) {
            throw new EntityNotFoundException(PostcodeLatLngEntity.class, postcode);
        }
        return postcodeLatLngMapper.toModel(foundEntity);
    }

    @Override
    @AuditTrail(action = "Update postcodeLatLng")
    @Transactional
    public PostcodeUpdateResponseModel updatePostcode(@Valid PostcodeUpdateRequestModel postcodeUpdateRequestModel) throws EntityNotFoundException {
        PostcodeLatLngEntity foundEntity = postcodeLatLngDao.findByPostcode(postcodeUpdateRequestModel.getPostcode());
        if (foundEntity == null) {
            throw new EntityNotFoundException(PostcodeLatLngEntity.class, postcodeUpdateRequestModel.getPostcode());
        }

        foundEntity.setLatitude(postcodeUpdateRequestModel.getLatitude());
        foundEntity.setLongitude(postcodeUpdateRequestModel.getLongitude());

        postcodeLatLngDao.save(foundEntity);
        return postcodeLatLngMapper.toUpdateResponseModel(foundEntity);

    }

}
