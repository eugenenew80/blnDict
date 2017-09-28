package kz.kegoc.bln.repository.dict.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import kz.kegoc.bln.entity.dict.Region;
import kz.kegoc.bln.repository.common.AbstractRepository;
import kz.kegoc.bln.repository.dict.RegionRepository;


@Stateless
public class RegionRepositoryImpl extends AbstractRepository<Region> implements RegionRepository {
	public RegionRepositoryImpl() { setClazz(Region.class); }

	public RegionRepositoryImpl(EntityManager entityManager) {
		this();
		setEntityManager(entityManager);
	}
}
