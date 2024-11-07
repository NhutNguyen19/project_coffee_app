package project.duan.qlybancafe.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.duan.qlybancafe.model.Food;

@Repository
public interface FoodRepository extends JpaRepository<Food, String> {
    List<Food> findByCategoryName(String category);

    boolean existsByName(String name);

    Optional<Food> findFoodByName(String name);

    List<Food> findByCategoryId(String id);
}
