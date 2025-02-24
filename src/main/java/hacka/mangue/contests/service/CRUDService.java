package hacka.mangue.contests.service;

import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CRUDService<ID, T> {

    List<T> findAll();
    T findById(ID id);
    T create(T entity);
    T update(ID id, T entity);
    void delete(ID id);
}
