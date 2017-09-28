package kz.kegoc.bln.repository.dict.impl;

import kz.kegoc.bln.entity.dict.EnergySourceCompany;
import kz.kegoc.bln.repository.common.AbstractRepository;
import kz.kegoc.bln.repository.dict.EnergySourceCompanyRepository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;


@Stateless
public class EnergySorceCompanyRepositoryImpl extends AbstractRepository<EnergySourceCompany> implements EnergySourceCompanyRepository {
	public EnergySorceCompanyRepositoryImpl() { setClazz(EnergySourceCompany.class); }

	public EnergySorceCompanyRepositoryImpl(EntityManager entityManager) {
		this();
		setEntityManager(entityManager);
	}
}
