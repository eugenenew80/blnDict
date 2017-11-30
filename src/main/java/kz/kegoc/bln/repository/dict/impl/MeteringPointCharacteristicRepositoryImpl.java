package kz.kegoc.bln.repository.dict.impl;

import kz.kegoc.bln.entity.dict.MeteringPointCharacteristic;
import kz.kegoc.bln.repository.common.AbstractRepository;
import kz.kegoc.bln.repository.dict.MeteringPointCharacteristicRepository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

@Stateless
public class MeteringPointCharacteristicRepositoryImpl extends AbstractRepository<MeteringPointCharacteristic> implements MeteringPointCharacteristicRepository {
	public MeteringPointCharacteristicRepositoryImpl() { setClazz(MeteringPointCharacteristic.class); }

	public MeteringPointCharacteristicRepositoryImpl(EntityManager entityManager) {
		this();
		setEntityManager(entityManager);
	}
}
