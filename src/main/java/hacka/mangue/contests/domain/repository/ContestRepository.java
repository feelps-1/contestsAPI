package hacka.mangue.contests.domain.repository;

import hacka.mangue.contests.domain.models.Contest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContestRepository extends JpaRepository<Contest, Long> {
    boolean existsByName(String name);
}
