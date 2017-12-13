package kz.kegoc.bln.repository.common;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.apache.commons.lang3.StringUtils;
import kz.kegoc.bln.entity.common.HasId;
import kz.kegoc.bln.repository.common.query.Query;


public abstract class AbstractRepository<T extends HasId> implements Repository<T> {

	public List<T> selectAll() {
		TypedQuery<T> query = em.createNamedQuery(clazz.getSimpleName() +  ".findAll", clazz);
		return query.getResultList();
	}


	public List<T> select(Query query) {
		if ( StringUtils.isEmpty(query.where()) )
			return selectAll();	
		
		String queryStr = "select t from " + clazz.getSimpleName() + " t" + " where " + query.where() + " " + query.orderBy();
		
		TypedQuery<T> typedQuery = getEntityManager().createQuery(queryStr.trim(), clazz);
    	query.params().keySet().stream()
    		.forEach(it -> typedQuery.setParameter(it, query.params().get(it).value) );

    	return typedQuery.getResultList();
	}

	
	public T selectById(Object entityId) {
		return em.find(clazz, entityId);
	}


	public T insert(T entity) {
		em.persist(entity);
		return entity;
	}

	
	public T update(T entity) {
		em.merge(entity);		
		return entity;
	}
	

	public boolean delete(Long entityId) {
		T entity = selectById(entityId);
		if (entity!=null) {
			em.remove(entity);
			return true;
		}
		return false;
	}
	
	
	
	public EntityManager getEntityManager() {
		return em;
	}

	public void setEntityManager(EntityManager em) {
		this.em = em;
	}	
		
	protected void setClazz(Class<T> clazz) {
		this.clazz = clazz;
	}

	protected Class<T> getClazz() {
		return clazz;
	}


	private Class<T> clazz;

	@PersistenceContext(unitName = "bln")
	private EntityManager em;

}
