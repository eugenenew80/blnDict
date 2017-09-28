package kz.kegoc.bln.repository.dict.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import kz.kegoc.bln.entity.dict.EnergySource;
import kz.kegoc.bln.repository.common.AbstractRepository;
import kz.kegoc.bln.repository.dict.EnergySourceRepository;


@Stateless
public class EnergySourceRepositoryImpl extends AbstractRepository<EnergySource> implements EnergySourceRepository {
	public EnergySourceRepositoryImpl() { setClazz(EnergySource.class); }

	public EnergySourceRepositoryImpl(EntityManager entityManager) {
		this();
		setEntityManager(entityManager);
	}
}
