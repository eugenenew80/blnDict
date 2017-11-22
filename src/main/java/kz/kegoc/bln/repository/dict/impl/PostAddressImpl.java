package kz.kegoc.bln.repository.dict.impl;

import kz.kegoc.bln.entity.dict.PostAddress;
import kz.kegoc.bln.repository.common.AbstractRepository;
import kz.kegoc.bln.repository.dict.PostAddressRepository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;


@Stateless
public class PostAddressImpl extends AbstractRepository<PostAddress> implements PostAddressRepository {
	public PostAddressImpl() { setClazz(PostAddress.class); }

	public PostAddressImpl(EntityManager entityManager) {
		this();
		setEntityManager(entityManager);
	}
}
