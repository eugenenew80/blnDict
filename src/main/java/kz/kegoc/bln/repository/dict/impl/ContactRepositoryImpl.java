package kz.kegoc.bln.repository.dict.impl;

import kz.kegoc.bln.entity.dict.Contact;
import kz.kegoc.bln.repository.common.AbstractRepository;
import kz.kegoc.bln.repository.dict.ContactRepository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;


@Stateless
public class ContactRepositoryImpl extends AbstractRepository<Contact> implements ContactRepository {
	public ContactRepositoryImpl() { setClazz(Contact.class); }

	public ContactRepositoryImpl(EntityManager entityManager) {
		this();
		setEntityManager(entityManager);
	}
}
