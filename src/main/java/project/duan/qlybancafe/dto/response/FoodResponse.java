package project.duan.qlybancafe.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import project.duan.qlybancafe.model.Category;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FoodResponse {
    String id;
    String name;
    double price;
    Category category;
}
