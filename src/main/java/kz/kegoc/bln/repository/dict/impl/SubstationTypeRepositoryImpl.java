package kz.kegoc.bln.repository.dict.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import kz.kegoc.bln.entity.dict.SubstationType;
import kz.kegoc.bln.repository.common.AbstractRepository;
import kz.kegoc.bln.repository.dict.SubstationTypeRepository;


@Stateless
public class SubstationTypeRepositoryImpl extends AbstractRepository<SubstationType> implements SubstationTypeRepository {
	public SubstationTypeRepositoryImpl() { setClazz(SubstationType.class); }

	public SubstationTypeRepositoryImpl(EntityManager entityManager) {
		this();
		setEntityManager(entityManager);
	}
}
