package kz.kegoc.bln.repository.dict.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import kz.kegoc.bln.entity.dict.MeterType;
import kz.kegoc.bln.repository.common.AbstractRepository;
import kz.kegoc.bln.repository.dict.MeterTypeRepository;


@Stateless
public class MeterTypeRepositoryImpl extends AbstractRepository<MeterType> implements MeterTypeRepository {
	public MeterTypeRepositoryImpl() { setClazz(MeterType.class); }

	public MeterTypeRepositoryImpl(EntityManager entityManager) {
		this();
		setEntityManager(entityManager);
	}
}
