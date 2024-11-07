package project.duan.qlybancafe.service.category;

import java.util.List;

import project.duan.qlybancafe.dto.request.CategoryRequest;
import project.duan.qlybancafe.dto.response.CategoryResponse;

public interface ICategoryService {
    CategoryResponse getCategoryById(String id);

    CategoryResponse getCategoryByName(String name);

    List<CategoryResponse> getAllCategories();

    CategoryResponse addCategory(CategoryRequest request);

    CategoryResponse updateCategory(CategoryRequest request, String id);

    void deleteCategoryById(String id);
}
