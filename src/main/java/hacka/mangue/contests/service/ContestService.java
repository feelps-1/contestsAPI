package hacka.mangue.contests.service;

import hacka.mangue.contests.domain.models.Contest;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContestService extends CRUDService<Long, Contest> {

}
