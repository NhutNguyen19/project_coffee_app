package project.duan.qlybancafe.model;

import jakarta.persistence.*;

import lombok.*;
import lombok.experimental.FieldDefaults;
import project.duan.qlybancafe.enums.Status;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TableFood {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    String name;
    Status status;
}
