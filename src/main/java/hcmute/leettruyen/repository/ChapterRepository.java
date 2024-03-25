package hcmute.leettruyen.repository;

import hcmute.leettruyen.entity.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter,Integer> {
}
