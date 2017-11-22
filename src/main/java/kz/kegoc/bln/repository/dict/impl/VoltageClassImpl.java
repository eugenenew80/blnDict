package kz.kegoc.bln.repository.dict.impl;

import kz.kegoc.bln.entity.dict.VoltageClass;
import kz.kegoc.bln.repository.common.AbstractRepository;
import kz.kegoc.bln.repository.dict.VoltageClassRepository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;


@Stateless
public class VoltageClassImpl extends AbstractRepository<VoltageClass> implements VoltageClassRepository {
	public VoltageClassImpl() { setClazz(VoltageClass.class); }

	public VoltageClassImpl(EntityManager entityManager) {
		this();
		setEntityManager(entityManager);
	}
}
