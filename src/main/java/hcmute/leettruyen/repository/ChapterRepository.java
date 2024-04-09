package hcmute.leettruyen.repository;

import hcmute.leettruyen.entity.Book;
import hcmute.leettruyen.entity.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter,Integer> {
    List<Chapter> findAllByBook(Book book);
}
