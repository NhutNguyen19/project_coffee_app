package project.duan.qlybancafe.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import project.duan.qlybancafe.dto.ApiResponse;
import project.duan.qlybancafe.dto.request.CategoryRequest;
import project.duan.qlybancafe.dto.response.CategoryResponse;
import project.duan.qlybancafe.service.category.ICategoryService;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CategoryController {
    ICategoryService iCategoryService;

    @GetMapping("/all")
    ApiResponse<List<CategoryResponse>> getAllCategories() {
        return ApiResponse.<List<CategoryResponse>>builder()
                .message("Category list")
                .result(iCategoryService.getAllCategories())
                .build();
    }

    @PostMapping("/add")
    ApiResponse<CategoryResponse> addCategory(@RequestBody CategoryRequest request) {
        return ApiResponse.<CategoryResponse>builder()
                .message("Add category successful")
                .result(iCategoryService.addCategory(request))
                .build();
    }

    @PutMapping("/update/{id}")
    ApiResponse<CategoryResponse> updateCategory(@RequestBody CategoryRequest request,@PathVariable String id) {
        return ApiResponse.<CategoryResponse>builder()
                .message("Update category successful")
                .result(iCategoryService.updateCategory(request, id))
                .build();
    }

    @GetMapping("/{id}")
    ApiResponse<CategoryResponse> getCategoryById(@PathVariable String id) {
        return ApiResponse.<CategoryResponse>builder()
                .message("Get category successful")
                .result(iCategoryService.getCategoryById(id))
                .build();
    }

    @GetMapping("/category-name/{name}")
    ApiResponse<CategoryResponse> getCategoryByName(@PathVariable String name){
        return ApiResponse.<CategoryResponse>builder()
                .message("Get category name successful")
                .result(iCategoryService.getCategoryByName(name))
                .build();
    }

    @DeleteMapping("/delete/{id}")
    ApiResponse<CategoryResponse> deleteCategory(@PathVariable String id){
        iCategoryService.deleteCategoryById(id);
        return ApiResponse.<CategoryResponse>builder().message("Delete successful").build();
    }
}
