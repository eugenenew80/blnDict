package kz.kegoc.bln.repository.dict.impl;

import kz.kegoc.bln.entity.dict.MeteringPointCurrentTrans;
import kz.kegoc.bln.repository.common.AbstractRepository;
import kz.kegoc.bln.repository.dict.MeteringPointCurrentTransRepository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;


@Stateless
public class MeteringPointCurrentTransRepositoryImpl extends AbstractRepository<MeteringPointCurrentTrans> implements MeteringPointCurrentTransRepository {
	public MeteringPointCurrentTransRepositoryImpl() { setClazz(MeteringPointCurrentTrans.class); }

	public MeteringPointCurrentTransRepositoryImpl(EntityManager entityManager) {
		this();
		setEntityManager(entityManager);
	}
}
