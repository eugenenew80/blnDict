package kz.kegoc.bln.repository.dict.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import kz.kegoc.bln.entity.dict.Company;
import kz.kegoc.bln.repository.common.AbstractRepository;
import kz.kegoc.bln.repository.dict.CompanyRepository;


@Stateless
public class CompanyRepositoryImpl extends AbstractRepository<Company> implements CompanyRepository {
	public CompanyRepositoryImpl() { setClazz(Company.class); }

	public CompanyRepositoryImpl(EntityManager entityManager) {
		this();
		setEntityManager(entityManager);
	}


	public Company selectByTin(String tin) {
		TypedQuery<Company> query = getEntityManager().createNamedQuery("Company.findByTin", Company.class);
		query.setParameter("tin", tin);

		return query.getResultList().stream()
			.findFirst()
			.orElse(null);
	}
}
