package kz.kegoc.bln.repository.dict.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import kz.kegoc.bln.entity.dict.VoltageTransType;
import kz.kegoc.bln.repository.common.AbstractRepository;
import kz.kegoc.bln.repository.dict.VoltageTransTypeRepository;


@Stateless
public class VoltageTransTypeRepositoryImpl extends AbstractRepository<VoltageTransType> implements VoltageTransTypeRepository {
	public VoltageTransTypeRepositoryImpl() { setClazz(VoltageTransType.class); }

	public VoltageTransTypeRepositoryImpl(EntityManager entityManager) {
		this();
		setEntityManager(entityManager);
	}
}
