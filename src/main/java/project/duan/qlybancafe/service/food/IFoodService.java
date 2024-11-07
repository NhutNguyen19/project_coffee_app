package project.duan.qlybancafe.service.food;

import java.util.List;

import project.duan.qlybancafe.dto.request.FoodRequest;
import project.duan.qlybancafe.dto.response.FoodResponse;

public interface IFoodService {
    FoodResponse addFood(FoodRequest request);

    FoodResponse getFoodById(String id);

    void deleteFoodById(String id);

    FoodResponse updateFood(FoodRequest request, String id);

    FoodResponse searchFoodByName(String name);

    List<FoodResponse> getAllFoods();

    List<FoodResponse> getFoodsByCategory(String category);
}
