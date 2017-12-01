package kz.kegoc.bln.repository.dict.impl;

import kz.kegoc.bln.entity.dict.ContactPhoneNumber;
import kz.kegoc.bln.repository.common.AbstractRepository;
import kz.kegoc.bln.repository.dict.ContactPhoneNumberRepository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;


@Stateless
public class ContactPhoneNumberRepositoryImpl extends AbstractRepository<ContactPhoneNumber> implements ContactPhoneNumberRepository {
	public ContactPhoneNumberRepositoryImpl() { setClazz(ContactPhoneNumber.class); }

	public ContactPhoneNumberRepositoryImpl(EntityManager entityManager) {
		this();
		setEntityManager(entityManager);
	}
}
