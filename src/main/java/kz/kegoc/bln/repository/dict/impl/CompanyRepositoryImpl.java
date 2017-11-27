package kz.kegoc.bln.repository.dict.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import kz.kegoc.bln.entity.dict.Organization;
import kz.kegoc.bln.repository.common.AbstractRepository;
import kz.kegoc.bln.repository.dict.CompanyRepository;

@Stateless
public class CompanyRepositoryImpl extends AbstractRepository<Organization> implements CompanyRepository {
	public CompanyRepositoryImpl() { setClazz(Organization.class); }

	public CompanyRepositoryImpl(EntityManager entityManager) {
		this();
		setEntityManager(entityManager);
	}


	public Organization selectByTin(String tin) {
		TypedQuery<Organization> query = getEntityManager().createNamedQuery("Organization.findByTin", Organization.class);
		query.setParameter("tin", tin);

		return query.getResultList().stream()
			.findFirst()
			.orElse(null);
	}
}
