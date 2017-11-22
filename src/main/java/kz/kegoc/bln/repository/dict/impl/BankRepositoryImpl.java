package kz.kegoc.bln.repository.dict.impl;

import kz.kegoc.bln.entity.dict.Bank;
import kz.kegoc.bln.repository.common.AbstractRepository;
import kz.kegoc.bln.repository.dict.BankRepository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;


@Stateless
public class BankRepositoryImpl extends AbstractRepository<Bank> implements BankRepository {
	public BankRepositoryImpl() { setClazz(Bank.class); }

	public BankRepositoryImpl(EntityManager entityManager) {
		this();
		setEntityManager(entityManager);
	}
}
