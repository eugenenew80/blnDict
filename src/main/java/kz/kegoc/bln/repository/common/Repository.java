package kz.kegoc.bln.repository.common;

import java.util.List;
import kz.kegoc.bln.entity.common.HasId;
import kz.kegoc.bln.repository.common.query.Query;

public interface Repository <T extends HasId> {
    List<T> selectAll();

    List<T> select(Query query);
    
    T selectById(Object entityId);
    
    T insert(T entity);

    T update(T entity);

    boolean delete(Long entityId);
}
