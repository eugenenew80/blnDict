package kz.kegoc.bln.repository.dict.impl;

import kz.kegoc.bln.entity.dict.PowerLine;
import kz.kegoc.bln.repository.common.AbstractRepository;
import kz.kegoc.bln.repository.dict.PowerLineRepository;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

@Stateless
public class PowerLineRepositoryImpl extends AbstractRepository<PowerLine> implements PowerLineRepository {
	public PowerLineRepositoryImpl() { setClazz(PowerLine.class); }

	public PowerLineRepositoryImpl(EntityManager entityManager) {
		this();
		setEntityManager(entityManager);
	}
}
