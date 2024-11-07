package project.duan.qlybancafe.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import project.duan.qlybancafe.dto.ApiResponse;
import project.duan.qlybancafe.dto.request.FoodRequest;
import project.duan.qlybancafe.dto.response.FoodResponse;
import project.duan.qlybancafe.service.food.IFoodService;

import java.util.List;

@RestController
@RequestMapping("/foods")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class FoodController {
    IFoodService iFoodService;

    @PostMapping("/add")
    ApiResponse<FoodResponse> addFood(@RequestBody FoodRequest request){
        return ApiResponse.<FoodResponse>builder()
                .message("Food added successfully")
                .result(iFoodService.addFood(request))
                .build();
    }

    @DeleteMapping("/delete/{id}")
    ApiResponse<Void> deleteFood(@PathVariable String id){
        iFoodService.deleteFoodById(id);
        return ApiResponse.<Void>builder()
                .message("Food deleted successfully")
                .build();
    }

    @GetMapping("/food/{id}")
    ApiResponse<FoodResponse> getFoodById(@PathVariable String id){
        return ApiResponse.<FoodResponse>builder()
                .message("Food retrieved successfully")
                .result(iFoodService.getFoodById(id))
                .build();
    }

    @GetMapping("/all")
    ApiResponse<List<FoodResponse>> getAllFoods(){
        return  ApiResponse.<List<FoodResponse>>builder()
                .message("Food retrieved successfully")
                .result(iFoodService.getAllFoods())
                .build();
    }

    @GetMapping("/search")
    ApiResponse<FoodResponse> searchFoodByName(@RequestParam String name){
        return ApiResponse.<FoodResponse>builder()
                .message("Food retrieved successfully")
                .result(iFoodService.searchFoodByName(name))
                .build();
    }

    @PutMapping("/update-food/{id}")
    ApiResponse<FoodResponse> updateFood(@RequestBody FoodRequest request, @PathVariable String id){
        return ApiResponse.<FoodResponse>builder()
                .message("Food updated successfully")
                .result(iFoodService.updateFood(request, id))
                .build();
    }

    @GetMapping("/food/category/{categoryName}")
    ApiResponse<List<FoodResponse>> getFoodByCategory(@PathVariable String categoryName){
        return ApiResponse.<List<FoodResponse>>builder()
                .message("Food retrieved successfully")
                .result(iFoodService.getFoodsByCategory(categoryName))
                .build();
    }
}
