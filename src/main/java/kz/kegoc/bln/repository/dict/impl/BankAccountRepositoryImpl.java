package kz.kegoc.bln.repository.dict.impl;

import kz.kegoc.bln.entity.dict.BankAccount;
import kz.kegoc.bln.repository.common.AbstractRepository;
import kz.kegoc.bln.repository.dict.BankAccountRepository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;


@Stateless
public class BankAccountRepositoryImpl extends AbstractRepository<BankAccount> implements BankAccountRepository {
	public BankAccountRepositoryImpl() { setClazz(BankAccount.class); }

	public BankAccountRepositoryImpl(EntityManager entityManager) {
		this();
		setEntityManager(entityManager);
	}
}
