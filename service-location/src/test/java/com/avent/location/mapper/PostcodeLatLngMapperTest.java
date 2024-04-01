package com.avent.location.mapper;

import com.avent.location.entity.PostcodeLatLngEntity;
import com.avent.location.model.PostcodeLatLngModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PostcodeLatLngMapperTest {
    private PostcodeLatLngMapper mapper;

    @BeforeEach
    public void setup() {
        mapper = Mappers.getMapper(PostcodeLatLngMapper.class);
    }

    @Test
    public void shouldMapEntityToModel() {
        PostcodeLatLngEntity entity = new PostcodeLatLngEntity();
        entity.setPostcode("A");
        entity.setLatitude(123.234);
        entity.setLongitude(234.345);

        PostcodeLatLngModel model = mapper.toModel(entity);

        assertEquals(entity.getPostcode(), model.getPostcode());
        assertEquals(entity.getLatitude(), model.getLatitude());
        assertEquals(entity.getLongitude(), model.getLongitude());
    }

    @Test
    public void shouldHandleNullEntity() {
        PostcodeLatLngEntity entity = null;

        PostcodeLatLngModel model = mapper.toModel(entity);

        assertEquals(null, model);
    }

    @Test
    public void shouldMapModelToEntity() {
        PostcodeLatLngModel model = new PostcodeLatLngModel();
        model.setPostcode("B");
        model.setLatitude(345.456);
        model.setLongitude(456.567);

        PostcodeLatLngEntity entity = mapper.toEntity(model);

        assertEquals(model.getPostcode(), entity.getPostcode());
        assertEquals(model.getLatitude(), entity.getLatitude());
        assertEquals(model.getLongitude(), entity.getLongitude());
    }

    @Test
    public void shouldHandleNullModel() {
        PostcodeLatLngModel model = null;

        PostcodeLatLngEntity entity = mapper.toEntity(model);

        assertEquals(null, entity);
    }

    @Test
    public void shouldMapEntityListToModelList() {
        List<PostcodeLatLngEntity> entities = new ArrayList<>();
        entities.add(new PostcodeLatLngEntity(null, "C", 567.678, 678.789));
        entities.add(new PostcodeLatLngEntity(null, "D", 789.890, 890.901));

        List<PostcodeLatLngModel> models = mapper.toModels(entities);

        assertEquals(entities.size(), models.size());
        for (int i = 0; i < entities.size(); i++) {
            assertEquals(entities.get(i).getPostcode(), models.get(i).getPostcode());
            assertEquals(entities.get(i).getLatitude(), models.get(i).getLatitude());
            assertEquals(entities.get(i).getLongitude(), models.get(i).getLongitude());
        }
    }

    @Test
    public void shouldHandleEmptyEntityList() {
        List<PostcodeLatLngEntity> entities = new ArrayList<>();

        List<PostcodeLatLngModel> models = mapper.toModels(entities);

        assertTrue(models.isEmpty());
    }

    @Test
    public void shouldMapModelListToEntityList() {
        List<PostcodeLatLngModel> models = new ArrayList<>();
        models.add(new PostcodeLatLngModel("E", 901.012, 12.123));
        models.add(new PostcodeLatLngModel("F", 123.234, 234.345));

        List<PostcodeLatLngEntity> entities = mapper.toEntities(models);

        assertEquals(models.size(), entities.size());
        for (int i = 0; i < models.size(); i++) {
            assertEquals(models.get(i).getPostcode(), entities.get(i).getPostcode());
            assertEquals(models.get(i).getLatitude(), entities.get(i).getLatitude());
            assertEquals(models.get(i).getLongitude(), entities.get(i).getLongitude());
        }
    }

    @Test
    public void shouldHandleEmptyModelList() {
        List<PostcodeLatLngModel> models = new ArrayList<>();

        List<PostcodeLatLngEntity> entities = mapper.toEntities(models);

        assertTrue(entities.isEmpty());
    }
}
