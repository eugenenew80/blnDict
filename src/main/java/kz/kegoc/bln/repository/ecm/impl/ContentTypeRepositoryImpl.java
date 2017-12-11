package kz.kegoc.bln.repository.ecm.impl;

import kz.kegoc.bln.entity.ecm.ContentType;
import kz.kegoc.bln.repository.common.AbstractRepository;
import kz.kegoc.bln.repository.ecm.ContentTypeRepository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

@Stateless
public class ContentTypeRepositoryImpl extends AbstractRepository<ContentType> implements ContentTypeRepository {
	public ContentTypeRepositoryImpl() { setClazz(ContentType.class); }

	public ContentTypeRepositoryImpl(EntityManager entityManager) {
		this();
		setEntityManager(entityManager);
	}
}
