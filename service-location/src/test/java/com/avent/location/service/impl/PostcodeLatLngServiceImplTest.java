package com.avent.location.service.impl;

import com.avent.exception.EntityNotFoundException;
import com.avent.location.dao.PostcodeLatLngDao;
import com.avent.location.entity.PostcodeLatLngEntity;
import com.avent.location.mapper.PostcodeLatLngMapper;
import com.avent.location.model.PostcodeLatLngModel;
import com.avent.location.model.request.PostcodeUpdateRequestModel;
import com.avent.location.model.response.PostcodeUpdateResponseModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class PostcodeLatLngServiceImplTest {

    @InjectMocks
    private PostcodeLatLngServiceImpl postcodeLatLngService;

    @Mock
    private PostcodeLatLngDao postcodeLatLngDao;

    @Mock
    private PostcodeLatLngMapper postcodeLatLngMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldFindByPostcodeIn() {
        List<String> postcodes = Arrays.asList("AB12 3CD", "EF45 6GH");
        List<PostcodeLatLngEntity> entities = Arrays.asList(new PostcodeLatLngEntity(), new PostcodeLatLngEntity());

        when(postcodeLatLngDao.findByPostcodeIn(postcodes)).thenReturn(entities);
        when(postcodeLatLngMapper.toModels(entities)).thenReturn(Arrays.asList(new PostcodeLatLngModel(), new PostcodeLatLngModel()));

        postcodeLatLngService.findByPostcodeIn(postcodes);

        verify(postcodeLatLngDao, times(1)).findByPostcodeIn(postcodes);
        verify(postcodeLatLngMapper, times(1)).toModels(entities);
    }

    @Test
    public void shouldFindByPostcode() throws EntityNotFoundException {
        String postcode = "AB12 3CD";
        PostcodeLatLngEntity entity = new PostcodeLatLngEntity();

        when(postcodeLatLngDao.findByPostcode(postcode)).thenReturn(entity);
        when(postcodeLatLngMapper.toModel(entity)).thenReturn(new PostcodeLatLngModel());

        postcodeLatLngService.findByPostcode(postcode);

        verify(postcodeLatLngDao, times(1)).findByPostcode(postcode);
        verify(postcodeLatLngMapper, times(1)).toModel(entity);
    }

    @Test
    public void shouldThrowEntityNotFoundExceptionWhenPostcodeNotFound() {
        String postcode = "AB12 3CD";

        when(postcodeLatLngDao.findByPostcode(postcode)).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () -> postcodeLatLngService.findByPostcode(postcode));
    }

    @Test
    public void shouldUpdatePostcode() throws EntityNotFoundException {
        PostcodeUpdateRequestModel requestModel = new PostcodeUpdateRequestModel();
        requestModel.setPostcode("AB12 3CD");
        requestModel.setLatitude(51.5074);
        requestModel.setLongitude(-0.1278);

        PostcodeLatLngEntity entity = new PostcodeLatLngEntity();
        entity.setPostcode(requestModel.getPostcode());
        entity.setLatitude(requestModel.getLatitude());
        entity.setLongitude(requestModel.getLongitude());

        when(postcodeLatLngDao.findByPostcode(requestModel.getPostcode())).thenReturn(entity);
        when(postcodeLatLngDao.save(entity)).thenReturn(entity);
        when(postcodeLatLngMapper.toUpdateResponseModel(entity)).thenReturn(new PostcodeUpdateResponseModel());

        postcodeLatLngService.updatePostcode(requestModel);

        verify(postcodeLatLngDao, times(1)).findByPostcode(requestModel.getPostcode());
        verify(postcodeLatLngDao, times(1)).save(entity);
        verify(postcodeLatLngMapper, times(1)).toUpdateResponseModel(entity);
    }

    @Test
    public void shouldThrowEntityNotFoundExceptionWhenUpdatingNonExistingPostcode() {
        PostcodeUpdateRequestModel requestModel = new PostcodeUpdateRequestModel();
        requestModel.setPostcode("AB12 3CD");
        requestModel.setLatitude(51.5074);
        requestModel.setLongitude(-0.1278);

        when(postcodeLatLngDao.findByPostcode(requestModel.getPostcode())).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () -> postcodeLatLngService.updatePostcode(requestModel));
    }
}
