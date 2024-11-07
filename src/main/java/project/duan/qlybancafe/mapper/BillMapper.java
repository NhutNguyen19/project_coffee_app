package project.duan.qlybancafe.mapper;

import org.mapstruct.Mapper;
import project.duan.qlybancafe.dto.request.BillRequest;
import project.duan.qlybancafe.dto.response.BillResponse;
import project.duan.qlybancafe.model.Bill;

@Mapper(componentModel = "spring")
public interface BillMapper {
    Bill toBill(BillRequest request);
    BillResponse toBillResponse(Bill bill);
}
