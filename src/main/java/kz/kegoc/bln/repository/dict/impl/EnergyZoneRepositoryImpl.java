package kz.kegoc.bln.repository.dict.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import kz.kegoc.bln.entity.dict.EnergyZone;
import kz.kegoc.bln.repository.common.AbstractRepository;
import kz.kegoc.bln.repository.dict.EnergyZoneRepository;


@Stateless
public class EnergyZoneRepositoryImpl extends AbstractRepository<EnergyZone> implements EnergyZoneRepository {
	public EnergyZoneRepositoryImpl() { setClazz(EnergyZone.class); }

	public EnergyZoneRepositoryImpl(EntityManager entityManager) {
		this();
		setEntityManager(entityManager);
	}
}
