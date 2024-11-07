package project.duan.qlybancafe.mapper;

import org.mapstruct.Mapper;

import org.mapstruct.MappingTarget;
import project.duan.qlybancafe.dto.request.FoodRequest;
import project.duan.qlybancafe.dto.response.FoodResponse;
import project.duan.qlybancafe.model.Food;

@Mapper(componentModel = "spring")
public interface FoodMapper {
    Food toFood(FoodRequest request);

    FoodResponse toFoodResponse(Food food);

    void updateFood(@MappingTarget Food food, FoodRequest request);
}
