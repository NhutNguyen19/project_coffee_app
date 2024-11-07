package project.duan.qlybancafe.model;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    String name;
    double price;

    @ManyToOne
    @JsonBackReference
    Category category;

    public Food(String name, double price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }
}
