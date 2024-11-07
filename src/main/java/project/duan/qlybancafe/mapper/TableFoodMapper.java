package project.duan.qlybancafe.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import project.duan.qlybancafe.dto.request.StatusRequest;
import project.duan.qlybancafe.dto.request.TableFoodRequest;
import project.duan.qlybancafe.dto.response.TableFoodResponse;
import project.duan.qlybancafe.model.TableFood;

@Mapper(componentModel = "spring")
public interface TableFoodMapper {
    TableFood toTableFood(TableFoodRequest request);
    TableFoodResponse toTableFoodResponse(TableFood tableFood);
    void updateTableFood(@MappingTarget TableFood tableFood, TableFoodRequest request);
    void switchTable(@MappingTarget TableFood tableFood, StatusRequest request);

}
