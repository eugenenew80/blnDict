package kz.kegoc.bln.repository.dict.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import kz.kegoc.bln.entity.dict.CurrentTransType;
import kz.kegoc.bln.repository.common.AbstractRepository;
import kz.kegoc.bln.repository.dict.CurrentTransTypeRepository;


@Stateless
public class CurrentTransTypeRepositoryImpl extends AbstractRepository<CurrentTransType> implements CurrentTransTypeRepository {
	public CurrentTransTypeRepositoryImpl() { setClazz(CurrentTransType.class); }

	public CurrentTransTypeRepositoryImpl(EntityManager entityManager) {
		this();
		setEntityManager(entityManager);
	}
}
