package kz.kegoc.bln.repository.dict.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import kz.kegoc.bln.entity.dict.CurrentTrans;
import kz.kegoc.bln.repository.common.AbstractRepository;
import kz.kegoc.bln.repository.dict.CurrentTransRepository;


@Stateless
public class CurrentTransRepositoryImpl extends AbstractRepository<CurrentTrans> implements CurrentTransRepository {
	public CurrentTransRepositoryImpl() { setClazz(CurrentTrans.class); }

	public CurrentTransRepositoryImpl(EntityManager entityManager) {
		this();
		setEntityManager(entityManager);
	}
}
