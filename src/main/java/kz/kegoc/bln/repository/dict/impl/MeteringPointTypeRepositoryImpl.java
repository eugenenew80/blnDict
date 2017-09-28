package kz.kegoc.bln.repository.dict.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import kz.kegoc.bln.entity.dict.MeteringPointType;
import kz.kegoc.bln.repository.common.AbstractRepository;
import kz.kegoc.bln.repository.dict.MeteringPointTypeRepository;


@Stateless
public class MeteringPointTypeRepositoryImpl extends AbstractRepository<MeteringPointType> implements MeteringPointTypeRepository {
	public MeteringPointTypeRepositoryImpl() { setClazz(MeteringPointType.class); }

	public MeteringPointTypeRepositoryImpl(EntityManager entityManager) {
		this();
		setEntityManager(entityManager);
	}
}
