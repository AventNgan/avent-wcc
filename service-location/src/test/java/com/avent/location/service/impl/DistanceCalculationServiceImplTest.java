package com.avent.location.service.impl;

import com.avent.base.util.RequestUtil;
import com.avent.location.service.impl.DistanceCalculationServiceImpl;
import com.avent.location.service.PostcodeLatLngService;
import com.avent.location.dao.PostcodeRequestLogDao;
import com.avent.location.model.PostcodeLatLngModel;
import com.avent.location.entity.PostcodeRequestLogEntity;
import com.avent.exception.EntityNotFoundException;
import com.avent.exception.InvalidEnumException;
import com.avent.exception.InvalidRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class DistanceCalculationServiceImplTest {

    @InjectMocks
    private DistanceCalculationServiceImpl distanceCalculationService;

    @Mock
    private PostcodeLatLngService postcodeLatLngService;

    @Mock
    private PostcodeRequestLogDao postcodeRequestLogDao;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldCalculateDistanceAndRetrieveInfoByPostcode() throws InvalidEnumException, EntityNotFoundException, InvalidRequestException {
        String location1 = "AB12 3CD";
        String location2 = "EF45 6GH";
        String unit = "km";

        PostcodeLatLngModel model1 = new PostcodeLatLngModel(location1, 51.5074, -0.1278);
        PostcodeLatLngModel model2 = new PostcodeLatLngModel(location2, 52.2053, 0.1218);

        when(postcodeLatLngService.findByPostcodeIn(Arrays.asList(location1, location2))).thenReturn(Arrays.asList(model1, model2));
        try (MockedStatic<RequestUtil> mocked = Mockito.mockStatic(RequestUtil.class)) {
            mocked.when(() -> RequestUtil.getData(RequestUtil.REQUEST_ID)).thenReturn("RequestID");

            distanceCalculationService.calculateDistanceAndRetrieveInfoByPostcode(location1, location2, unit);

            verify(postcodeRequestLogDao, times(1)).saveAll(any(List.class));
        }


    }

    @Test
    public void shouldThrowEntityNotFoundExceptionWhenPostcodeNotFound() {
        String location1 = "AB12 3CD";
        String location2 = "EF45 6GH";
        String unit = "km";

        when(postcodeLatLngService.findByPostcodeIn(Arrays.asList(location1, location2))).thenReturn(Arrays.asList(new PostcodeLatLngModel(location1, 51.5074, -0.1278)));

        assertThrows(EntityNotFoundException.class, () -> distanceCalculationService.calculateDistanceAndRetrieveInfoByPostcode(location1, location2, unit));
    }

    @Test
    public void shouldThrowInvalidRequestExceptionWhenCoordinateIsNull() {
        String location1 = "AB12 3CD";
        String location2 = "EF45 6GH";
        String unit = "km";

        when(postcodeLatLngService.findByPostcodeIn(Arrays.asList(location1, location2))).thenReturn(Arrays.asList(new PostcodeLatLngModel(location1, null, -0.1278), new PostcodeLatLngModel(location2, 52.2053, 0.1218)));

        assertThrows(InvalidRequestException.class, () -> distanceCalculationService.calculateDistanceAndRetrieveInfoByPostcode(location1, location2, unit));
    }

    @Test
    public void shouldCalculateDistanceByCoordinates() throws InvalidEnumException, InvalidRequestException {
        Double lat1 = 1.0;
        Double lng1 = 1.0;
        Double lat2 = 1.1;
        Double lng2 = 1.1;
        String unit = "km";

        BigDecimal result = distanceCalculationService.calculateDistanceByCoordinates(lat1, lng1, lat2, lng2, unit);

        assertEquals(BigDecimal.valueOf(15.72403), result.setScale(5, BigDecimal.ROUND_HALF_UP));
    }

    @Test
    public void shouldThrowInvalidRequestExceptionWhenAnyCoordinateIsNull() {
        Double lat1 = 51.5074;
        Double lng1 = null;
        Double lat2 = 52.2053;
        Double lng2 = 0.1218;
        String unit = "km";

        assertThrows(InvalidRequestException.class, () -> distanceCalculationService.calculateDistanceByCoordinates(lat1, lng1, lat2, lng2, unit));
    }
}
