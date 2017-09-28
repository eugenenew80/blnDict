package kz.kegoc.bln.repository.dict.impl;

import kz.kegoc.bln.entity.dict.SubstationMeteringPoint;
import kz.kegoc.bln.repository.common.AbstractRepository;
import kz.kegoc.bln.repository.dict.SubstationMeteringPointRepository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;


@Stateless
public class SubstationMeteringPointRepositoryImpl extends AbstractRepository<SubstationMeteringPoint> implements SubstationMeteringPointRepository {
	public SubstationMeteringPointRepositoryImpl() { setClazz(SubstationMeteringPoint.class); }

	public SubstationMeteringPointRepositoryImpl(EntityManager entityManager) {
		this();
		setEntityManager(entityManager);
	}
}
