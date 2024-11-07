package project.duan.qlybancafe.service.category;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import project.duan.qlybancafe.dto.request.CategoryRequest;
import project.duan.qlybancafe.dto.response.CategoryResponse;
import project.duan.qlybancafe.dto.response.FoodResponse;
import project.duan.qlybancafe.exception.AlreadyExistsException;
import project.duan.qlybancafe.mapper.CategoryMapper;
import project.duan.qlybancafe.model.Category;
import project.duan.qlybancafe.model.Food;
import project.duan.qlybancafe.repository.CategoryRepository;
import project.duan.qlybancafe.repository.FoodRepository;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CategoryService implements ICategoryService {
    CategoryRepository categoryRepository;
    CategoryMapper categoryMapper;
    FoodRepository foodRepository;

    @Override
    public CategoryResponse getCategoryById(String id) {
        return categoryMapper.toCategoryResponse(
                categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found!")));
    }

    @Override
    public CategoryResponse getCategoryByName(String name) {
        return categoryMapper.toCategoryResponse(categoryRepository.findByName(name).orElseThrow(() -> new RuntimeException("Category name not found!")));
    }

    @Override
    public List<CategoryResponse> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::toCategoryResponse)
                .toList();
    }

    @Override
    public CategoryResponse addCategory(CategoryRequest request) {
        if (categoryRepository.existsByName(request.getName())) {
            throw new RuntimeException("Category name already exists!");
        }
        Category category = categoryMapper.toCategory(request);
        categoryRepository.save(category);
        return categoryMapper.toCategoryResponse(category);
    }


    @Override
    public CategoryResponse updateCategory(CategoryRequest request, String id) {
        Category category =
                categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found!"));
        categoryMapper.updateAccount(category, request);
        category = categoryRepository.save(category);
        return categoryMapper.toCategoryResponse(category);
    }

    @Override
    public void deleteCategoryById(String id) {
        List<Food> foods = foodRepository.findByCategoryId(id);
        if (!foods.isEmpty()) {
            foodRepository.deleteAll(foods);
        }
        categoryRepository.deleteById(id);
    }

}
