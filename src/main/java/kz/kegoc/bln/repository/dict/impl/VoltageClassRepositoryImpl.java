package kz.kegoc.bln.repository.dict.impl;

import kz.kegoc.bln.entity.dict.VoltageClass;
import kz.kegoc.bln.repository.common.AbstractRepository;
import kz.kegoc.bln.repository.dict.VoltageClassRepository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;


@Stateless
public class VoltageClassRepositoryImpl extends AbstractRepository<VoltageClass> implements VoltageClassRepository {
	public VoltageClassRepositoryImpl() { setClazz(VoltageClass.class); }

	public VoltageClassRepositoryImpl(EntityManager entityManager) {
		this();
		setEntityManager(entityManager);
	}
}
