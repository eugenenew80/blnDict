package kz.kegoc.bln.repository.dict.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import kz.kegoc.bln.entity.dict.Unit;
import kz.kegoc.bln.repository.common.AbstractRepository;
import kz.kegoc.bln.repository.dict.UnitRepository;


@Stateless
public class UnitRepositoryImpl extends AbstractRepository<Unit> implements UnitRepository {
	public UnitRepositoryImpl() { setClazz(Unit.class); }

	public UnitRepositoryImpl(EntityManager entityManager) {
		this();
		setEntityManager(entityManager);
	}
}
