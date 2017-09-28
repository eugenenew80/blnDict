package kz.kegoc.bln.repository.dict.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import kz.kegoc.bln.entity.dict.MeteringType;
import kz.kegoc.bln.repository.common.AbstractRepository;
import kz.kegoc.bln.repository.dict.MeteringTypeRepository;


@Stateless
public class MeteringTypeRepositoryImpl extends AbstractRepository<MeteringType> implements MeteringTypeRepository {
	public MeteringTypeRepositoryImpl() { setClazz(MeteringType.class); }

	public MeteringTypeRepositoryImpl(EntityManager entityManager) {
		this();
		setEntityManager(entityManager);
	}
}
