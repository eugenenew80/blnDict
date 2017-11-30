package kz.kegoc.bln.repository.dict.impl;

import kz.kegoc.bln.entity.dict.EnergySourceBusinessPartner;
import kz.kegoc.bln.repository.common.AbstractRepository;
import kz.kegoc.bln.repository.dict.EnergySourceBusinessPartnerRepository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;


@Stateless
public class EnergySorceBusinessPartnerRepositoryImpl extends AbstractRepository<EnergySourceBusinessPartner> implements EnergySourceBusinessPartnerRepository {
	public EnergySorceBusinessPartnerRepositoryImpl() { setClazz(EnergySourceBusinessPartner.class); }

	public EnergySorceBusinessPartnerRepositoryImpl(EntityManager entityManager) {
		this();
		setEntityManager(entityManager);
	}
}
