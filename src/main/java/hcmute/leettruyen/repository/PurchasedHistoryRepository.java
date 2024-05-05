package hcmute.leettruyen.repository;

import hcmute.leettruyen.entity.Chapter;
import hcmute.leettruyen.entity.PurchasedHistory;
import hcmute.leettruyen.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchasedHistoryRepository extends JpaRepository<PurchasedHistory,Integer> {
    PurchasedHistory findByUserAndChapter(User user, Chapter chapter);
    List<PurchasedHistory> findByUser(User user);

}
