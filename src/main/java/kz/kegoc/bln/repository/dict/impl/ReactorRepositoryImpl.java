package kz.kegoc.bln.repository.dict.impl;

import kz.kegoc.bln.entity.dict.Reactor;
import kz.kegoc.bln.repository.common.AbstractRepository;
import kz.kegoc.bln.repository.dict.ReactorRepository;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class ReactorRepositoryImpl extends AbstractRepository<Reactor> implements ReactorRepository {
	public ReactorRepositoryImpl() { setClazz(Reactor.class); }

	public ReactorRepositoryImpl(EntityManager entityManager) {
		this();
		setEntityManager(entityManager);
	}

	public List<Reactor> selectByOrg(List<Long> orgList) {
		Class<Reactor> clazz = Reactor.class;
		TypedQuery<Reactor> query = getEntityManager().createNamedQuery(clazz.getSimpleName() +  ".findByOrg", clazz);
		query.setParameter("orgList", orgList);
		return query.getResultList();
	}
}
