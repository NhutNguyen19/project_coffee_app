package project.duan.qlybancafe.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;

import lombok.*;
import lombok.experimental.FieldDefaults;
import project.duan.qlybancafe.enums.StatusBill;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    LocalDateTime checkIn;
    LocalDateTime checkOut;

    StatusBill status;

    @ManyToOne(fetch = FetchType.EAGER)
    TableFood table;

    BigDecimal discount;
    BigDecimal totalPrice;
}
