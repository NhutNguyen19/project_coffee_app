package project.duan.qlybancafe.service.tableFood;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import project.duan.qlybancafe.dto.request.StatusRequest;
import project.duan.qlybancafe.dto.request.TableFoodRequest;
import project.duan.qlybancafe.dto.response.TableFoodResponse;
import project.duan.qlybancafe.enums.Status;
import project.duan.qlybancafe.exception.AlreadyExistsException;
import project.duan.qlybancafe.exception.NotFoundException;
import project.duan.qlybancafe.mapper.TableFoodMapper;
import project.duan.qlybancafe.model.TableFood;
import project.duan.qlybancafe.repository.TableFoodRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class TableFoodService implements ITableFoodService {
    TableFoodRepository tableFoodRepository;
    TableFoodMapper tableFoodMapper;

    @Override
    public TableFoodResponse addTableFood(TableFoodRequest request) throws AlreadyExistsException {
        if (tableFoodRepository.existsByName(request.getName())) throw new AlreadyExistsException("Table food already exists");
        request.setStatus(Status.PENDING);
        TableFood food = tableFoodMapper.toTableFood(request);
        return tableFoodMapper.toTableFoodResponse(tableFoodRepository.save(food));
    }

    @Override
    public TableFoodResponse switchTable(StatusRequest request, String id) {
        TableFood tableFood = tableFoodRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Table food not found"));

        Status status = request.getStatus();
        if (status == null) {
            throw new IllegalArgumentException("Status cannot be null");
        }

        tableFood.setStatus(status);
        tableFoodMapper.switchTable(tableFood, request);
        tableFoodRepository.save(tableFood);

        return tableFoodMapper.toTableFoodResponse(tableFood);
    }

    @Override
    public List<TableFoodResponse> getAllTableFood() {
        return tableFoodRepository.findAll().stream()
                .map(tableFoodMapper::toTableFoodResponse)
                .toList();
    }

    @Override
    public TableFoodResponse getTableFoodById(String id) {
        return tableFoodMapper.toTableFoodResponse(tableFoodRepository.findById(id).orElseThrow(() -> new NotFoundException("table id not found")));
    }

    @Override
    public void deleteTableFood(String id) {
        tableFoodRepository.deleteById(id);
    }
}
