package kz.kegoc.bln.repository.dict.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import kz.kegoc.bln.entity.dict.VoltageTrans;
import kz.kegoc.bln.repository.common.AbstractRepository;
import kz.kegoc.bln.repository.dict.VoltageTransRepository;


@Stateless
public class VoltageTransRepositoryImpl extends AbstractRepository<VoltageTrans> implements VoltageTransRepository {
	public VoltageTransRepositoryImpl() { setClazz(VoltageTrans.class); }

	public VoltageTransRepositoryImpl(EntityManager entityManager) {
		this();
		setEntityManager(entityManager);
	}
}
