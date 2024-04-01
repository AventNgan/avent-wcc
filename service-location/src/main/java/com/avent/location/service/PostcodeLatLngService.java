package com.avent.location.service;

import com.avent.exception.EntityNotFoundException;
import com.avent.location.model.PostcodeLatLngModel;
import com.avent.location.model.request.PostcodeUpdateRequestModel;
import com.avent.location.model.response.PostcodeUpdateResponseModel;

import java.util.List;

public interface PostcodeLatLngService {
    List<PostcodeLatLngModel> findByPostcodeIn(List<String> postcodes);
    PostcodeLatLngModel findByPostcode(String postcodes) throws EntityNotFoundException;

    PostcodeUpdateResponseModel updatePostcode(PostcodeUpdateRequestModel postcodeUpdateRequestModel) throws EntityNotFoundException;
}
