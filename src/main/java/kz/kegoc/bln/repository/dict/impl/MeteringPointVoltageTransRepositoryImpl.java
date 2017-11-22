package kz.kegoc.bln.repository.dict.impl;

import kz.kegoc.bln.entity.dict.MeteringPointVoltageTrans;
import kz.kegoc.bln.repository.common.AbstractRepository;
import kz.kegoc.bln.repository.dict.MeteringPointVoltageTransRepository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;


@Stateless
public class MeteringPointVoltageTransRepositoryImpl extends AbstractRepository<MeteringPointVoltageTrans> implements MeteringPointVoltageTransRepository {
	public MeteringPointVoltageTransRepositoryImpl() { setClazz(MeteringPointVoltageTrans.class); }

	public MeteringPointVoltageTransRepositoryImpl(EntityManager entityManager) {
		this();
		setEntityManager(entityManager);
	}
}
