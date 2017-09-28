package kz.kegoc.bln.repository.dict.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import kz.kegoc.bln.entity.dict.AccountingType;
import kz.kegoc.bln.repository.common.AbstractRepository;
import kz.kegoc.bln.repository.dict.AccountingTypeRepository;


@Stateless
public class AccountingTypeRepositoryImpl extends AbstractRepository<AccountingType> implements AccountingTypeRepository {
	public AccountingTypeRepositoryImpl() { setClazz(AccountingType.class); }

	public AccountingTypeRepositoryImpl(EntityManager entityManager) {
		this();
		setEntityManager(entityManager);
	}
}
