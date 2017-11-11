package kz.kegoc.bln.repository.dict.impl;

import kz.kegoc.bln.entity.dict.Country;
import kz.kegoc.bln.repository.common.AbstractRepository;
import kz.kegoc.bln.repository.dict.CountryRepository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;


@Stateless
public class CountryRepositoryImpl extends AbstractRepository<Country> implements CountryRepository {
	public CountryRepositoryImpl() { setClazz(Country.class); }

	public CountryRepositoryImpl(EntityManager entityManager) {
		this();
		setEntityManager(entityManager);
	}
}
