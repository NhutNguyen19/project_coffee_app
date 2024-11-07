package project.duan.qlybancafe.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BillInfoResponse {
    String id;
    String foodId;
    String billId;
    int count;
}
