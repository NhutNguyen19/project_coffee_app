package project.duan.qlybancafe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.duan.qlybancafe.model.TableFood;

import java.util.Optional;

@Repository
public interface TableFoodRepository extends JpaRepository<TableFood, String> {
    boolean existsByName(String name);
    Optional<TableFood> findByName(String name);
}
