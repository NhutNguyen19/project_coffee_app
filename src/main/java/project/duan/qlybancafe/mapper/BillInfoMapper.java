package project.duan.qlybancafe.mapper;

import org.mapstruct.Mapper;
import project.duan.qlybancafe.dto.request.BillInfoRequest;
import project.duan.qlybancafe.dto.response.BillInfoResponse;
import project.duan.qlybancafe.dto.response.BillInfoResponseDefault;
import project.duan.qlybancafe.model.BillInfo;

@Mapper(componentModel = "spring")
public interface BillInfoMapper {
    BillInfo toBillInfo(BillInfoRequest request);
    default BillInfoResponse toBillInfoResponseInsert(BillInfo billInfo){
        BillInfoResponse response = new BillInfoResponse();
        response.setId(billInfo.getId());
        response.setFoodId(billInfo.getFood().getId());
        response.setBillId(billInfo.getBill().getId());
        response.setCount(billInfo.getCount());
        return response;
    };

    BillInfoResponseDefault toBillInfoResponse(BillInfo billInfo);

}
