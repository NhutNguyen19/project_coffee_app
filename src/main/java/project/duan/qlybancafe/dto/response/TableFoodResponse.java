package project.duan.qlybancafe.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import project.duan.qlybancafe.enums.Status;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TableFoodResponse {
    String id;
    String name;
    Status status;

}
