package kz.kegoc.bln.repository.dict.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import kz.kegoc.bln.entity.dict.EnergySourceType;
import kz.kegoc.bln.repository.common.AbstractRepository;
import kz.kegoc.bln.repository.dict.EnergySourceTypeRepository;


@Stateless
public class EnergySourceTypeRepositoryImpl extends AbstractRepository<EnergySourceType> implements EnergySourceTypeRepository {
	public EnergySourceTypeRepositoryImpl() { setClazz(EnergySourceType.class); }

	public EnergySourceTypeRepositoryImpl(EntityManager entityManager) {
		this();
		setEntityManager(entityManager);
	}
}
