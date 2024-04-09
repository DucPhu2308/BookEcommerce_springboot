package hcmute.leettruyen.repository;

import hcmute.leettruyen.entity.Book;
import hcmute.leettruyen.entity.Rating;
import hcmute.leettruyen.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface RatingRepository extends JpaRepository<Rating,Integer> {
    List<Rating> findAllByBook(Book book);
    boolean existsByBookAndUser(Book book, User user);
}
