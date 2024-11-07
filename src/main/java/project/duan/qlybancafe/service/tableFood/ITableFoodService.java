package project.duan.qlybancafe.service.tableFood;

import project.duan.qlybancafe.dto.request.StatusRequest;
import project.duan.qlybancafe.dto.request.TableFoodRequest;
import project.duan.qlybancafe.dto.response.TableFoodResponse;
import project.duan.qlybancafe.exception.AlreadyExistsException;

import java.util.List;

public interface ITableFoodService {
    TableFoodResponse addTableFood(TableFoodRequest request) throws AlreadyExistsException;
    TableFoodResponse switchTable(StatusRequest request, String id);
    List<TableFoodResponse> getAllTableFood();
    TableFoodResponse getTableFoodById(String id);
    void deleteTableFood(String id);

}
