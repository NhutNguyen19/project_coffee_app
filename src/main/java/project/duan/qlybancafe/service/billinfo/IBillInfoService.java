package project.duan.qlybancafe.service.billinfo;

import project.duan.qlybancafe.dto.request.BillInfoRequest;
import project.duan.qlybancafe.dto.response.BillInfoResponse;
import project.duan.qlybancafe.dto.response.BillInfoResponseDefault;

import java.util.List;

public interface IBillInfoService {
    BillInfoResponse insertBillInfo(BillInfoRequest request);
    List<BillInfoResponseDefault> getListBillInfo();
    void deleteBillInfoByFoodId(String idFood);
}
