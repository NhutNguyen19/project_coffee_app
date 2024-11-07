package project.duan.qlybancafe.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import project.duan.qlybancafe.enums.Status;
import project.duan.qlybancafe.enums.StatusBill;
import project.duan.qlybancafe.model.TableFood;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BillRequest {
    LocalDateTime checkIn;
    LocalDateTime checkOut;
    StatusBill status;
    TableFood table;
    BigDecimal discount;
    BigDecimal totalPrice;
}
