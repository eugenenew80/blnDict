package kz.kegoc.bln.repository.dict.impl;

import kz.kegoc.bln.entity.dict.EnergySourceMeteringPoint;
import kz.kegoc.bln.repository.common.AbstractRepository;
import kz.kegoc.bln.repository.dict.EnergySourceMeteringPointRepository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;


@Stateless
public class EnergySorceMeteringPointRepositoryImpl extends AbstractRepository<EnergySourceMeteringPoint> implements EnergySourceMeteringPointRepository {
	public EnergySorceMeteringPointRepositoryImpl() { setClazz(EnergySourceMeteringPoint.class); }

	public EnergySorceMeteringPointRepositoryImpl(EntityManager entityManager) {
		this();
		setEntityManager(entityManager);
	}
}
