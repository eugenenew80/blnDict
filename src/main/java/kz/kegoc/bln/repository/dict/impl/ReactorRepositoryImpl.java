package kz.kegoc.bln.repository.dict.impl;

import kz.kegoc.bln.entity.dict.Reactor;
import kz.kegoc.bln.repository.common.AbstractRepositoryOrg;
import kz.kegoc.bln.repository.dict.ReactorRepository;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

@Stateless
public class ReactorRepositoryImpl extends AbstractRepositoryOrg<Reactor> implements ReactorRepository {
	public ReactorRepositoryImpl() { setClazz(Reactor.class); }

	public ReactorRepositoryImpl(EntityManager entityManager) {
		this();
		setEntityManager(entityManager);
	}
}
