package kz.kegoc.bln.repository.dict.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import kz.kegoc.bln.entity.dict.Organization;
import kz.kegoc.bln.repository.common.AbstractRepository;
import kz.kegoc.bln.repository.dict.OrganizationRepository;

@Stateless
public class OrganizationRepositoryImpl extends AbstractRepository<Organization> implements OrganizationRepository {
	public OrganizationRepositoryImpl() { setClazz(Organization.class); }

	public OrganizationRepositoryImpl(EntityManager entityManager) {
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
