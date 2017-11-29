package kz.kegoc.bln.repository.dict.impl;

import kz.kegoc.bln.entity.dict.ContactEmail;
import kz.kegoc.bln.repository.common.AbstractRepository;
import kz.kegoc.bln.repository.dict.ContactEmailRepository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;


@Stateless
public class ContactEmailRepositoryImpl extends AbstractRepository<ContactEmail> implements ContactEmailRepository {
	public ContactEmailRepositoryImpl() { setClazz(ContactEmail.class); }

	public ContactEmailRepositoryImpl(EntityManager entityManager) {
		this();
		setEntityManager(entityManager);
	}
}
