package hcmute.leettruyen.repository;

import hcmute.leettruyen.entity.Paragraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParagraphRepository extends JpaRepository<Paragraph,Integer> {
}
