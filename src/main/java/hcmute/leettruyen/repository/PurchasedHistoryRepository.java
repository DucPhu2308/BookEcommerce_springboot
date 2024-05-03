package hcmute.leettruyen.repository;

import hcmute.leettruyen.entity.Chapter;
import hcmute.leettruyen.entity.PurchasedHistory;
import hcmute.leettruyen.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchasedHistoryRepository extends JpaRepository<PurchasedHistory,Integer> {
    PurchasedHistory findByUserAndChapter(User user, Chapter chapter);
}
