package hcmute.leettruyen.repository;

import hcmute.leettruyen.entity.BookHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookHistoryRepository extends JpaRepository<BookHistory, Integer> {
    List<BookHistory> findByUserReadIdOrderByViewedAtDesc(Integer userId);
    BookHistory findByUserReadIdAndBookReadId(Integer userId, Integer bookId);
}
