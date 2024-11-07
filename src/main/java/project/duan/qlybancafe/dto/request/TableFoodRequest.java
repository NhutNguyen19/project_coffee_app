package project.duan.qlybancafe.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import project.duan.qlybancafe.enums.Status;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TableFoodRequest {
    String name;
    Status status;
}
