package kz.kegoc.bln.repository.dict.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import kz.kegoc.bln.entity.dict.Substation;
import kz.kegoc.bln.repository.common.AbstractRepositoryOrg;
import kz.kegoc.bln.repository.dict.SubstationRepository;

@Stateless
public class SubstationRepositoryImpl extends AbstractRepositoryOrg<Substation> implements SubstationRepository {
	public SubstationRepositoryImpl() { setClazz(Substation.class); }

	public SubstationRepositoryImpl(EntityManager entityManager) {
		this();
		setEntityManager(entityManager);
	}
}
