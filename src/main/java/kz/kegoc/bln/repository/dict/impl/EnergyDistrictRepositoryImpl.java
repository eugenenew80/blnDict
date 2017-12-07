package kz.kegoc.bln.repository.dict.impl;

import kz.kegoc.bln.entity.dict.EnergyDistrict;
import kz.kegoc.bln.repository.common.AbstractRepository;
import kz.kegoc.bln.repository.dict.EnergyDistrictRepository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;


@Stateless
public class EnergyDistrictRepositoryImpl extends AbstractRepository<EnergyDistrict> implements EnergyDistrictRepository {
	public EnergyDistrictRepositoryImpl() { setClazz(EnergyDistrict.class); }

	public EnergyDistrictRepositoryImpl(EntityManager entityManager) {
		this();
		setEntityManager(entityManager);
	}
}
