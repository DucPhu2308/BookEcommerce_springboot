package hcmute.leettruyen.repository;

import hcmute.leettruyen.entity.PurchasedHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchasedHistoryRepository extends JpaRepository<PurchasedHistory,Integer> {
}
