package project.duan.qlybancafe.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import project.duan.qlybancafe.dto.ApiResponse;
import project.duan.qlybancafe.dto.request.BillInfoRequest;
import project.duan.qlybancafe.dto.response.BillInfoResponse;
import project.duan.qlybancafe.dto.response.BillInfoResponseDefault;
import project.duan.qlybancafe.model.BillInfo;
import project.duan.qlybancafe.service.billinfo.IBillInfoService;

import java.util.List;

@RestController
@RequestMapping("/bill_info")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class BillInfoController {

    IBillInfoService billInfoService;

    @PostMapping("/insert")
    ApiResponse<BillInfoResponse> insertBillInfo(@RequestBody BillInfoRequest request){
        return ApiResponse.<BillInfoResponse>builder()
                .message("Insert bill info")
                .result(billInfoService.insertBillInfo(request))
                .build();
    }

    @GetMapping("/all")
    ApiResponse<List<BillInfoResponseDefault>> getListBillInfo(){
        return ApiResponse.<List<BillInfoResponseDefault>>builder()
                .message("Get list bill info")
                .result(billInfoService.getListBillInfo())
                .build();
    }

    @DeleteMapping("/delete_bill_info/{idFood}")
    ApiResponse<Void> deleteBillInfoByFoodId(@PathVariable String idFood){
        billInfoService.deleteBillInfoByFoodId(idFood);
        return ApiResponse.<Void>builder()
                .message("Delete bill info")
                .build();
    }
}
