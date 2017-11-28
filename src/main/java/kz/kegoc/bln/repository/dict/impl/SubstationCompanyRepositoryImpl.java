package kz.kegoc.bln.repository.dict.impl;

import kz.kegoc.bln.entity.dict.SubstationBusinessPartner;
import kz.kegoc.bln.repository.common.AbstractRepository;
import kz.kegoc.bln.repository.dict.SubstationCompanyRepository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;


@Stateless
public class SubstationCompanyRepositoryImpl extends AbstractRepository<SubstationBusinessPartner> implements SubstationCompanyRepository {
	public SubstationCompanyRepositoryImpl() { setClazz(SubstationBusinessPartner.class); }

	public SubstationCompanyRepositoryImpl(EntityManager entityManager) {
		this();
		setEntityManager(entityManager);
	}
}
