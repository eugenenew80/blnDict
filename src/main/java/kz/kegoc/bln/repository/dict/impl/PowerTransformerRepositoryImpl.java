package kz.kegoc.bln.repository.dict.impl;

import kz.kegoc.bln.entity.dict.PowerTransformer;
import kz.kegoc.bln.repository.common.AbstractRepository;
import kz.kegoc.bln.repository.dict.PowerTransformerRepository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

@Stateless
public class PowerTransformerRepositoryImpl extends AbstractRepository<PowerTransformer> implements PowerTransformerRepository {
	public PowerTransformerRepositoryImpl() { setClazz(PowerTransformer.class); }

	public PowerTransformerRepositoryImpl(EntityManager entityManager) {
		this();
		setEntityManager(entityManager);
	}
}
