package kz.kegoc.bln.repository.dict.impl;

import kz.kegoc.bln.entity.dict.PowerLineType;
import kz.kegoc.bln.repository.common.AbstractRepository;
import kz.kegoc.bln.repository.dict.PowerLineTypeRepository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

@Stateless
public class PowerLineTypeRepositoryImpl extends AbstractRepository<PowerLineType> implements PowerLineTypeRepository {
	public PowerLineTypeRepositoryImpl() { setClazz(PowerLineType.class); }

	public PowerLineTypeRepositoryImpl(EntityManager entityManager) {
		this();
		setEntityManager(entityManager);
	}
}
