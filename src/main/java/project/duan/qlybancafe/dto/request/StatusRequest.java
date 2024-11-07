package project.duan.qlybancafe.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import project.duan.qlybancafe.enums.Status;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StatusRequest {
    Status status;
}
