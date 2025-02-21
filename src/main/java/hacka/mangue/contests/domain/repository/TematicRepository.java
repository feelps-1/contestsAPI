package hacka.mangue.contests.domain.repository;

import hacka.mangue.contests.domain.models.Tematic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TematicRepository extends JpaRepository<Tematic, Long> {
}
