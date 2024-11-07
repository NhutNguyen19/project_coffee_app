package project.duan.qlybancafe.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import project.duan.qlybancafe.model.Bill;
import project.duan.qlybancafe.model.Food;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BillInfoRequest {
    Food food;
    Bill bill;
    int count;
}
