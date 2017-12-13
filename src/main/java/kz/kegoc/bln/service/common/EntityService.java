package kz.kegoc.bln.service.common;

import java.util.List;
import kz.kegoc.bln.webapi.filters.SessionContext;
import kz.kegoc.bln.entity.common.HasId;
import kz.kegoc.bln.repository.common.query.Query;

public interface EntityService<T extends HasId> {
	List<T> findAll(SessionContext context);

	List<T> find(Query query, SessionContext context);
	
	T findById(Object entityId, SessionContext context);

	T create(T entity, SessionContext context);

	T update(T entity, SessionContext context);

    boolean delete(Long entityId, SessionContext context);
}
