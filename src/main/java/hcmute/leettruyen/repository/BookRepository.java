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
    @Query("SELECT t FROM Book t WHERE LOWER( cast(unaccent(t.title) as string)) LIKE lower(cast(unaccent(concat('%', :keyword, '%')) as string ))")
    List<Book> findByTitleContainingIgnoreCaseCustom(String keyword);
    List<Book> findByOrderByAvgRatingDesc();
    List<Book> findByOrderByViewsDesc();
    List<Book> findByOrderByBuysDesc();
    // find <num> books sort by date with
    // <num> is the number of books to find
    @Query("SELECT b FROM Book b ORDER BY b.updatedAt DESC LIMIT:num")
    List<Book> findTopNByOrderByPublicDateDesc(int num);
}
