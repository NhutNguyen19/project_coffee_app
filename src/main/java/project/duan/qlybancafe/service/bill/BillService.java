package project.duan.qlybancafe.service.bill;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import project.duan.qlybancafe.dto.request.BillRequest;
import project.duan.qlybancafe.dto.response.BillResponse;
import project.duan.qlybancafe.enums.StatusBill;
import project.duan.qlybancafe.mapper.BillMapper;
import project.duan.qlybancafe.model.Bill;
import project.duan.qlybancafe.model.TableFood;
import project.duan.qlybancafe.repository.BillRepository;
import project.duan.qlybancafe.repository.TableFoodRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class BillService implements IBillService{

    BillRepository billRepository;
    BillMapper billMapper;
    private final TableFoodRepository tableFoodRepository;

    @Override
    public List<BillResponse> getBillListDate(LocalDateTime checkIn, LocalDateTime checkOut) {
        return billRepository.findAllByCheckInBetween(checkIn, checkOut);
    }

    @Override
    public Long countBillsBetweenDates(LocalDateTime checkIn, LocalDateTime checkOut) {
        return billRepository.countAllByCheckInBetween(checkIn, checkOut);
    }

    @Override
    public BillResponse checkOut(String id) {
        Bill bill = billRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Bill not found"));
        bill.setCheckOut(LocalDateTime.now());
        bill.setStatus(StatusBill.COMPLETE);
        billRepository.save(bill);
        return billMapper.toBillResponse(bill);
    }

    @Override
    public BillResponse insertBill(BillRequest request) {
        Bill bill = billMapper.toBill(request);

        TableFood tableFood = tableFoodRepository.findById(request.getTable().getId())
                .orElseThrow(() -> new RuntimeException("Table not found"));
        bill.setTable(tableFood);

        bill.setCheckIn(LocalDateTime.now());
        billRepository.save(bill);
        return billMapper.toBillResponse(bill);
    }
}
