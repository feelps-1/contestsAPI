package hacka.mangue.contests.domain.repository;

import hacka.mangue.contests.domain.models.Scholarity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScholarityRepository extends JpaRepository<Scholarity, Long> {
}
