package project.duan.qlybancafe.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import project.duan.qlybancafe.dto.request.CategoryRequest;
import project.duan.qlybancafe.dto.response.CategoryResponse;
import project.duan.qlybancafe.model.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toCategory(CategoryRequest request);

    CategoryResponse toCategoryResponse(Category category);

    void updateAccount(@MappingTarget Category category, CategoryRequest request);
}
