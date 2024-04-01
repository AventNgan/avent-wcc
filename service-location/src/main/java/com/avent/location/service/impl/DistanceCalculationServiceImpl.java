package com.avent.location.service.impl;

import com.avent.base.util.ObjectUtil;
import com.avent.base.util.RequestUtil;
import com.avent.exception.EntityNotFoundException;
import com.avent.exception.InvalidEnumException;
import com.avent.exception.InvalidRequestException;
import com.avent.location.constant.LengthUnitEnum;
import com.avent.location.dao.PostcodeRequestLogDao;
import com.avent.location.entity.PostcodeLatLngEntity;
import com.avent.location.entity.PostcodeRequestLogEntity;
import com.avent.location.model.PostcodeLatLngModel;
import com.avent.location.model.base.CoordinatesModel;
import com.avent.location.model.base.PostcodeLocationModel;
import com.avent.location.model.response.DistanceCalculationResponseModel;
import com.avent.location.service.DistanceCalculationService;
import com.avent.location.service.PostcodeLatLngService;
import com.avent.location.util.DistanceCalculationUtil;
import com.avent.location.util.DistanceUnitConversionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DistanceCalculationServiceImpl implements DistanceCalculationService {

    private final PostcodeLatLngService postcodeLatLngService;
    private final PostcodeRequestLogDao postcodeRequestLogDao;

    @Override
    @Transactional
    public DistanceCalculationResponseModel calculateDistanceAndRetrieveInfoByPostcode(String location1, String location2, String unit) throws InvalidEnumException, EntityNotFoundException, InvalidRequestException {
        List<String> postcodes = Arrays.asList(location1, location2);
        List<PostcodeLatLngModel> postcodeData = postcodeLatLngService.findByPostcodeIn(postcodes);
        if (postcodeData.size() != 2) {
            List<String> foundPostCodes = postcodeData.stream().map(PostcodeLatLngModel::getPostcode).collect(Collectors.toList());

            String notFound = postcodes.stream()
                    .filter(x -> !foundPostCodes.contains(x))
                    .collect(Collectors.joining(", "));

            throw new EntityNotFoundException(PostcodeLatLngEntity.class, notFound);

        }

        for (PostcodeLatLngModel postcodeDatum : postcodeData) {

            validateCoordinate(postcodeDatum);
        }

        BigDecimal distance = calculateDistanceByCoordinates(postcodeData.get(0).getLatitude(), postcodeData.get(0).getLongitude(), postcodeData.get(1).getLatitude(), postcodeData.get(1).getLongitude(), unit);

        List<PostcodeRequestLogEntity> postcodeRequestLogEntities = postcodeData.stream()
                .map(x -> new PostcodeRequestLogEntity(RequestUtil.getData(RequestUtil.REQUEST_ID).toString(), x.getPostcode(), x.getLatitude(), x.getLongitude()))
                .collect(Collectors.toList());

        postcodeRequestLogDao.saveAll(postcodeRequestLogEntities);

        return new DistanceCalculationResponseModel(
                new PostcodeLocationModel(location1, new CoordinatesModel(postcodeData.get(0).getLatitude(), postcodeData.get(0).getLongitude())),
                new PostcodeLocationModel(location2, new CoordinatesModel(postcodeData.get(1).getLatitude(), postcodeData.get(1).getLongitude())),
                distance, unit);
    }

    @Override
    public BigDecimal calculateDistanceByPostCode(String location1, String location2, String unit) throws InvalidEnumException, InvalidRequestException {
        List<PostcodeLatLngModel> postcodeData = postcodeLatLngService.findByPostcodeIn(Arrays.asList(location1, location2));
        return calculateDistanceByCoordinates(postcodeData.get(0).getLatitude(), postcodeData.get(0).getLongitude(), postcodeData.get(1).getLatitude(), postcodeData.get(1).getLongitude(), unit);

    }

    @Override
    public BigDecimal calculateDistanceByCoordinates(Double lat1, Double lng1, Double lat2, Double lng2, String unit) throws InvalidEnumException, InvalidRequestException {
        if (ObjectUtil.isAnyNull(lat1, lng1, lat2, lng2)) {
            throw new InvalidRequestException("Coordinate is null");
        }
        LengthUnitEnum lengthUnitEnum = LengthUnitEnum.fromName(unit);
        return BigDecimal.valueOf(DistanceUnitConversionUtil.convert(DistanceCalculationUtil.calculateDistanceMeter(lat1, lng1, lat2, lng2), lengthUnitEnum));
    }

    private void validateCoordinate(PostcodeLatLngModel model) throws InvalidRequestException {
        if (ObjectUtil.isAnyNull(model.getLatitude(), model.getLongitude())) {
            throw new InvalidRequestException("Coordinate is null: ".concat(model.getPostcode()));
        }

    }
}
