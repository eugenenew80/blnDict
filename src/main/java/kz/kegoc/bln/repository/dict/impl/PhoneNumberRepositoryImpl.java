package kz.kegoc.bln.repository.dict.impl;

import kz.kegoc.bln.entity.dict.PhoneNumber;
import kz.kegoc.bln.repository.common.AbstractRepository;
import kz.kegoc.bln.repository.dict.PhoneNumberRepository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;


@Stateless
public class PhoneNumberRepositoryImpl extends AbstractRepository<PhoneNumber> implements PhoneNumberRepository {
	public PhoneNumberRepositoryImpl() { setClazz(PhoneNumber.class); }

	public PhoneNumberRepositoryImpl(EntityManager entityManager) {
		this();
		setEntityManager(entityManager);
	}
}
