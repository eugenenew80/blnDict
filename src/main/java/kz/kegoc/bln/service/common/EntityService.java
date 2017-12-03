package kz.kegoc.bln.service.common;

import java.util.List;

import kz.kegoc.bln.ejb.SessionContext;
import kz.kegoc.bln.entity.common.HasId;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.repository.common.query.Query;

public interface EntityService<T extends HasId> {
	List<T> findAll();
	List<T> findAll(SessionContext context);

	List<T> find(Query query);
	List<T> find(Query query, SessionContext context);
	
	T findById(Object entityId);
	T findById(Object entityId, SessionContext context);

	T create(T entity);
	T create(T entity, SessionContext context);

	T update(T entity);
	T update(T entity, SessionContext context);

    boolean delete(Long entityId);
    boolean delete(Long entityId, SessionContext context);

    void setLang(Lang lang);
}
