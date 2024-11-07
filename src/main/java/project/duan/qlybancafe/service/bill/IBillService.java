package project.duan.qlybancafe.service.bill;

import project.duan.qlybancafe.dto.request.BillRequest;
import project.duan.qlybancafe.dto.response.BillResponse;

import java.time.LocalDateTime;
import java.util.List;

public interface IBillService {
    List<BillResponse> getBillListDate(LocalDateTime checkIn, LocalDateTime checkOut);
    Long countBillsBetweenDates(LocalDateTime checkIn, LocalDateTime checkOut);
    BillResponse checkOut(String id);
    BillResponse insertBill(BillRequest request);
}
