package kz.kegoc.bln.repository.dict.impl;

import kz.kegoc.bln.entity.dict.SubstationBusinessPartner;
import kz.kegoc.bln.repository.common.AbstractRepository;
import kz.kegoc.bln.repository.dict.SubstationBusinessPartnerRepository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;


@Stateless
public class SubstationBusinessPartnerRepositoryImpl extends AbstractRepository<SubstationBusinessPartner> implements SubstationBusinessPartnerRepository {
	public SubstationBusinessPartnerRepositoryImpl() { setClazz(SubstationBusinessPartner.class); }

	public SubstationBusinessPartnerRepositoryImpl(EntityManager entityManager) {
		this();
		setEntityManager(entityManager);
	}
}
