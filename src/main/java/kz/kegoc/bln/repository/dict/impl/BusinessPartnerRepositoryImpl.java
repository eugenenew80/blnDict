package kz.kegoc.bln.repository.dict.impl;

import kz.kegoc.bln.entity.dict.BusinessPartner;
import kz.kegoc.bln.repository.common.AbstractRepository;
import kz.kegoc.bln.repository.dict.BusinessPartnerRepository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;


@Stateless
public class BusinessPartnerRepositoryImpl extends AbstractRepository<BusinessPartner> implements BusinessPartnerRepository {
	public BusinessPartnerRepositoryImpl() { setClazz(BusinessPartner.class); }

	public BusinessPartnerRepositoryImpl(EntityManager entityManager) {
		this();
		setEntityManager(entityManager);
	}
}
