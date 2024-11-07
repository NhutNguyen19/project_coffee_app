package project.duan.qlybancafe.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import project.duan.qlybancafe.model.Bill;
import project.duan.qlybancafe.model.Food;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BillInfoResponseDefault {
    String id;
    Food food;
    Bill bill;
    int count;
}
