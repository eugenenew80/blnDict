package kz.kegoc.bln.repository.common;

import kz.kegoc.bln.entity.common.HasId;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public interface JpaRepository<T extends HasId> extends Repository<T>  {
    List<T> select(CriteriaQuery<T> query);

    EntityManager getEntityManager();

    Class<T> getClazz();
}
