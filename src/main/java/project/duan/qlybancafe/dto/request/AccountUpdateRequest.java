package project.duan.qlybancafe.dto.request;

import java.util.List;

import jakarta.validation.constraints.Size;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class AccountUpdateRequest {

    @Size(min = 4, message = "PASSWORD_INVALID")
    String password;

    String displayName;

    String phone;
    List<String> roles;
}
