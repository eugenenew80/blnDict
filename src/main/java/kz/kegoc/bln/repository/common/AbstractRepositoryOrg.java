package kz.kegoc.bln.repository.common;

import kz.kegoc.bln.entity.common.HasId;
import javax.persistence.TypedQuery;
import java.util.List;

public class AbstractRepositoryOrg<T extends HasId> extends AbstractRepository<T> implements RepositoryOrg<T> {
    public List<T> selectByOrg(List<Long> orgList) {
        TypedQuery<T> query = getEntityManager().createNamedQuery(getClazz().getSimpleName() +  ".findByOrg", getClazz());
        query.setParameter("orgList", orgList);
        return query.getResultList();
    }
}
