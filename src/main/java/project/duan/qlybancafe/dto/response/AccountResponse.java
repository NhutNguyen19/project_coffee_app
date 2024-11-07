package project.duan.qlybancafe.dto.response;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import project.duan.qlybancafe.model.Role;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponse {
    String id;
    String username;

    @JsonIgnore
    String password;
    String displayName;

    String phone;
    Set<Role> roles;
}
