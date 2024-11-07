package project.duan.qlybancafe.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.duan.qlybancafe.dto.response.BillResponse;
import project.duan.qlybancafe.model.Bill;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, String> {
    List<BillResponse> findAllByCheckInBetween(LocalDateTime checkIn, LocalDateTime checkOut);

//    @Query("SELECT b FROM Bill b WHERE b.checkIn BETWEEN :startDate AND :endDate")
//    Page<BillResponse> findAllByCheckInAndPage(LocalDateTime checkIn,LocalDateTime checkOut, Pageable pageable);
    Long countAllByCheckInBetween(LocalDateTime checkIn, LocalDateTime checkOut);

//    Bill findTopOrderByIdDesc();
}
