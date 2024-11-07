package project.duan.qlybancafe.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import project.duan.qlybancafe.dto.ApiResponse;
import project.duan.qlybancafe.dto.request.BillRequest;
import project.duan.qlybancafe.dto.response.BillResponse;
import project.duan.qlybancafe.service.bill.IBillService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/bills")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class BillController {
    IBillService iBillService;

    @GetMapping("/by-date")
    ApiResponse<List<BillResponse>> getBillListDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime checkIn,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime checkOut){
        return ApiResponse.<List<BillResponse>>builder()
                .message("List bill to date")
                .result(iBillService.getBillListDate(checkIn, checkOut))
                .build();
    }

    @GetMapping("/count-by-date")
    ApiResponse<Long> countBillsBetweenDates(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime checkIn,
                                             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime checkOut) {
        return ApiResponse.<Long>builder()
                .message("Count bills between dates")
                .result(iBillService.countBillsBetweenDates(checkIn, checkOut))
                .build();
    }

    @PostMapping("/checkout")
    ApiResponse<BillResponse> checkout(@RequestParam String id){
        return ApiResponse.<BillResponse>builder()
                .message("check out")
                .result(iBillService.checkOut(id))
                .build();
    }

    @PostMapping("/insert")
    ApiResponse<BillResponse> insertBill(@RequestBody BillRequest request){
        return ApiResponse.<BillResponse>builder()
                .message("Insert bill")
                .result(iBillService.insertBill(request))
                .build();
    }
}
