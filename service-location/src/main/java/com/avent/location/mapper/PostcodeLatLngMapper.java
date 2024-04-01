package com.avent.location.mapper;

import com.avent.location.entity.PostcodeLatLngEntity;
import com.avent.location.model.PostcodeLatLngModel;
import com.avent.location.model.response.PostcodeUpdateResponseModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostcodeLatLngMapper {
    PostcodeLatLngModel toModel(PostcodeLatLngEntity entity);
    PostcodeLatLngEntity toEntity(PostcodeLatLngModel model);
    List<PostcodeLatLngModel> toModels(List<PostcodeLatLngEntity> entities);
    List<PostcodeLatLngEntity> toEntities(List<PostcodeLatLngModel> models);

    PostcodeUpdateResponseModel toUpdateResponseModel(PostcodeLatLngEntity entity);
}
