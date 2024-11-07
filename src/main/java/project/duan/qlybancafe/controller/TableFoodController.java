package project.duan.qlybancafe.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import project.duan.qlybancafe.dto.ApiResponse;
import project.duan.qlybancafe.dto.request.StatusRequest;
import project.duan.qlybancafe.dto.request.TableFoodRequest;
import project.duan.qlybancafe.dto.response.TableFoodResponse;
import project.duan.qlybancafe.exception.AlreadyExistsException;
import project.duan.qlybancafe.service.tableFood.ITableFoodService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tables")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TableFoodController {
    ITableFoodService iTableFoodService;

    @PostMapping("/add")
    ApiResponse<TableFoodResponse> addTableFood(@RequestBody TableFoodRequest request) throws AlreadyExistsException {
        return ApiResponse.<TableFoodResponse>builder()
                .message("Successfully added table food")
                .result(iTableFoodService.addTableFood(request))
                .build();
    }

    @PutMapping("/switch-table/{id}")
    ApiResponse<TableFoodResponse> updateTableFood(@RequestBody StatusRequest request, @PathVariable String id) {
        return ApiResponse.<TableFoodResponse>builder()
                .message("Successfully updated table food")
                .result(iTableFoodService.switchTable(request, id))
                .build();
    }

    @GetMapping("/all")
    ApiResponse<List<TableFoodResponse>> getAllTableFoods() {
        return ApiResponse.<List<TableFoodResponse>>builder()
                .message("Successfully retrieved all table foods")
                .result(iTableFoodService.getAllTableFood())
                .build();
    }

    @GetMapping("/table/{id}")
    ApiResponse<TableFoodResponse> getTableFoodById(@PathVariable String id) {
        return ApiResponse.<TableFoodResponse>builder()
                .message("Successfully retrieved table food")
                .result(iTableFoodService.getTableFoodById(id))
                .build();
    }

    @DeleteMapping("/delete-table/{id}")
    ApiResponse<Void> deleteTableFoodById(@PathVariable String id) {
        iTableFoodService.deleteTableFood(id);
        return ApiResponse.<Void>builder()
                .message("Successfully deleted table food")
                .build();
    }
}
