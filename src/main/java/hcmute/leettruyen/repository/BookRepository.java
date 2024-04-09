package hcmute.leettruyen.repository;

import hcmute.leettruyen.entity.Book;
import hcmute.leettruyen.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
    List<Book> findAllByGenresContains(Genre genre);
    @Query("SELECT AVG(r.star) FROM Rating r WHERE r.book = :book")
    Float findAverageRatingByBook(@Param("book") Book book);
}
