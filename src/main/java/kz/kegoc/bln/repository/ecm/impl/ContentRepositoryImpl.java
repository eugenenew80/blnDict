package kz.kegoc.bln.repository.ecm.impl;

import kz.kegoc.bln.entity.ecm.Content;
import kz.kegoc.bln.repository.common.AbstractRepository;
import kz.kegoc.bln.repository.ecm.ContentRepository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

@Stateless
public class ContentRepositoryImpl extends AbstractRepository<Content> implements ContentRepository {
	public ContentRepositoryImpl() { setClazz(Content.class); }

	public ContentRepositoryImpl(EntityManager entityManager) {
		this();
		setEntityManager(entityManager);
	}
}
