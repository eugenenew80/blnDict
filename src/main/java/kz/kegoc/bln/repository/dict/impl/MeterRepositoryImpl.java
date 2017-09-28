package kz.kegoc.bln.repository.dict.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import kz.kegoc.bln.entity.dict.Meter;
import kz.kegoc.bln.repository.common.AbstractRepository;
import kz.kegoc.bln.repository.dict.MeterRepository;


@Stateless
public class MeterRepositoryImpl extends AbstractRepository<Meter> implements MeterRepository {
	public MeterRepositoryImpl() { setClazz(Meter.class); }

	public MeterRepositoryImpl(EntityManager entityManager) {
		this();
		setEntityManager(entityManager);
	}
}
