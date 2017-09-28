package kz.kegoc.bln.repository.dict.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import kz.kegoc.bln.entity.dict.EnergyNode;
import kz.kegoc.bln.repository.common.AbstractRepository;
import kz.kegoc.bln.repository.dict.EnergyNodeRepository;


@Stateless
public class EnergyNodeRepositoryImpl extends AbstractRepository<EnergyNode> implements EnergyNodeRepository {
	public EnergyNodeRepositoryImpl() { setClazz(EnergyNode.class); }

	public EnergyNodeRepositoryImpl(EntityManager entityManager) {
		this();
		setEntityManager(entityManager);
	}
}
