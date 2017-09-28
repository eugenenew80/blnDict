package kz.kegoc.bln.repository.dict.impl;

import kz.kegoc.bln.entity.dict.SubstationCompany;
import kz.kegoc.bln.repository.common.AbstractRepository;
import kz.kegoc.bln.repository.dict.SubstationCompanyRepository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;


@Stateless
public class SubstationCompanyRepositoryImpl extends AbstractRepository<SubstationCompany> implements SubstationCompanyRepository {
	public SubstationCompanyRepositoryImpl() { setClazz(SubstationCompany.class); }

	public SubstationCompanyRepositoryImpl(EntityManager entityManager) {
		this();
		setEntityManager(entityManager);
	}
}
