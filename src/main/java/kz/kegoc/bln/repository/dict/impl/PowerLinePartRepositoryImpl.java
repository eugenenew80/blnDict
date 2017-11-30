package kz.kegoc.bln.repository.dict.impl;

import kz.kegoc.bln.entity.dict.PowerLinePart;
import kz.kegoc.bln.repository.common.AbstractRepository;
import kz.kegoc.bln.repository.dict.PowerLinePartRepository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

@Stateless
public class PowerLinePartRepositoryImpl extends AbstractRepository<PowerLinePart> implements PowerLinePartRepository {
	public PowerLinePartRepositoryImpl() { setClazz(PowerLinePart.class); }

	public PowerLinePartRepositoryImpl(EntityManager entityManager) {
		this();
		setEntityManager(entityManager);
	}
}
