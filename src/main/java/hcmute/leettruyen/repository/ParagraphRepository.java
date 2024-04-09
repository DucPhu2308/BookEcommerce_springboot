package hcmute.leettruyen.repository;

import hcmute.leettruyen.entity.Chapter;
import hcmute.leettruyen.entity.Paragraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParagraphRepository extends JpaRepository<Paragraph,Integer> {
    List<Paragraph> findAllByChapter(Chapter chapter);
}
