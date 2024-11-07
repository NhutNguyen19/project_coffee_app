package project.duan.qlybancafe.service.billinfo;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import project.duan.qlybancafe.dto.request.BillInfoRequest;
import project.duan.qlybancafe.dto.response.BillInfoResponse;
import project.duan.qlybancafe.dto.response.BillInfoResponseDefault;
import project.duan.qlybancafe.mapper.BillInfoMapper;
import project.duan.qlybancafe.model.Bill;
import project.duan.qlybancafe.model.BillInfo;
import project.duan.qlybancafe.model.Category;
import project.duan.qlybancafe.model.Food;
import project.duan.qlybancafe.repository.BillInfoRepository;
import project.duan.qlybancafe.repository.BillRepository;
import project.duan.qlybancafe.repository.FoodRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class BillInfoService implements IBillInfoService{

    BillInfoRepository billInfoRepository;
    FoodRepository foodRepository;
    BillInfoMapper billInfoMapper;
    BillRepository billRepository;

    @Override
    public BillInfoResponse insertBillInfo(BillInfoRequest request) {
        try {
            BillInfo billInfo = billInfoMapper.toBillInfo(request);
            billInfoRepository.save(billInfo);
            return billInfoMapper.toBillInfoResponseInsert(billInfo);
        } catch (Exception e) {
            throw new RuntimeException("Error inserting bill info", e);
        }
    }

    @Override
    public List<BillInfoResponseDefault> getListBillInfo() {
        return billInfoRepository.findAll().stream().map(billInfoMapper::toBillInfoResponse).toList();
    }

    @Override
    public void deleteBillInfoByFoodId(String idFood) {
        Optional<Food> food = foodRepository.findById(idFood);
        billInfoRepository.deleteById(String.valueOf(food));
    }
}
