package project.duan.qlybancafe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.duan.qlybancafe.model.BillInfo;

@Repository
public interface BillInfoRepository extends JpaRepository<BillInfo, String> {
}
