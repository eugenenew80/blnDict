package kz.kegoc.bln.repository.dict.impl;

import kz.kegoc.bln.entity.dict.PostAddress;
import kz.kegoc.bln.repository.common.AbstractRepository;
import kz.kegoc.bln.repository.dict.PostAddressRepository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;


@Stateless
public class PostAddressRepositoryImpl extends AbstractRepository<PostAddress> implements PostAddressRepository {
	public PostAddressRepositoryImpl() { setClazz(PostAddress.class); }

	public PostAddressRepositoryImpl(EntityManager entityManager) {
		this();
		setEntityManager(entityManager);
	}
}
