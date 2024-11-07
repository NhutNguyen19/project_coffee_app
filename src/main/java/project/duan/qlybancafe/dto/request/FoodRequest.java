package project.duan.qlybancafe.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import project.duan.qlybancafe.model.Category;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FoodRequest {
    String name;
    double price;
    Category category;
}
