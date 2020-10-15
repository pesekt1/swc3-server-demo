package swc3.server2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import swc3.server2.model.Tutorial;

import java.util.List;

public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
    List<Tutorial> findByTitle(String title);
    List<Tutorial> findByPublished(boolean published);
}
