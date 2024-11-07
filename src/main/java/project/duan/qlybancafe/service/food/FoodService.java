package project.duan.qlybancafe.service.food;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import project.duan.qlybancafe.dto.request.FoodRequest;
import project.duan.qlybancafe.dto.response.FoodResponse;
import project.duan.qlybancafe.mapper.FoodMapper;
import project.duan.qlybancafe.model.Category;
import project.duan.qlybancafe.model.Food;
import project.duan.qlybancafe.repository.CategoryRepository;
import project.duan.qlybancafe.repository.FoodRepository;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class FoodService implements IFoodService {
    FoodRepository foodRepository;
    CategoryRepository categoryRepository;
    FoodMapper foodMapper;

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public FoodResponse addFood(FoodRequest request) {
        if (foodRepository.existsByName(request.getName())) {
            throw new RuntimeException("Food name exists");
        }
        if (request.getCategory() == null || request.getCategory().getName() == null) {
            request.setCategory(new Category("Default Category"));
        }
        String categoryName = request.getCategory().getName();
        Optional<Category> optionalCategory = categoryRepository.findByName(categoryName);
        Category category = optionalCategory.orElseGet(() -> {
            Category newCategory = new Category(categoryName);
            return categoryRepository.save(newCategory);
        });
        Food food = foodMapper.toFood(request);
        food.setCategory(category);
        foodRepository.save(food);
        return foodMapper.toFoodResponse(food);
    }

    @Override
    public FoodResponse getFoodById(String id) {
        return foodMapper.toFoodResponse(foodRepository.findById(id).orElseThrow(() -> new RuntimeException("Food not found")));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteFoodById(String id) {
        foodRepository.deleteById(id);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public FoodResponse updateFood(FoodRequest request, String id) {
        Food food = foodRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Food not found"));
        foodMapper.updateFood(food, request);
        if (request.getCategory() != null && request.getCategory().getName() != null) {
            String categoryName = request.getCategory().getName();
            Optional<Category> optionalCategory = categoryRepository.findByName(categoryName);
            Category category = optionalCategory.orElseGet(() -> {
                Category newCategory = new Category(categoryName);
                return categoryRepository.save(newCategory);
            });
            food.setCategory(category);
        }
        foodRepository.save(food);
        return foodMapper.toFoodResponse(food);
    }

    @Override
    public FoodResponse searchFoodByName(String name) {
        Food food = foodRepository.findFoodByName(name)
                .orElseThrow(() -> new RuntimeException("Food not found"));
        return foodMapper.toFoodResponse(food);
    }


    @Override
    public List<FoodResponse> getAllFoods() {
        return foodRepository.findAll().stream().map(foodMapper::toFoodResponse).toList();
    }

    @Override
    public List<FoodResponse> getFoodsByCategory(String categoryName) {
        List<Food> foods = foodRepository.findByCategoryName(categoryName);
        return foods.stream()
                .map(foodMapper::toFoodResponse)
                .collect(Collectors.toList());
    }
}
