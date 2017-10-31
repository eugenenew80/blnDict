package kz.kegoc.bln.repository.dict.impl;

import kz.kegoc.bln.entity.dict.MeteringPointMeter;
import kz.kegoc.bln.repository.common.AbstractRepository;
import kz.kegoc.bln.repository.dict.MeteringPointMeterRepository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;


@Stateless
public class MeteringPointMeterRepositoryImpl extends AbstractRepository<MeteringPointMeter> implements MeteringPointMeterRepository {
	public MeteringPointMeterRepositoryImpl() { setClazz(MeteringPointMeter.class); }

	public MeteringPointMeterRepositoryImpl(EntityManager entityManager) {
		this();
		setEntityManager(entityManager);
	}
}
