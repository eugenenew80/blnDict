package kz.kegoc.bln.repository.dict.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import kz.kegoc.bln.entity.dict.DataSource;
import kz.kegoc.bln.repository.common.AbstractRepository;
import kz.kegoc.bln.repository.dict.DataSourceRepository;


@Stateless
public class DataSourceRepositoryImpl extends AbstractRepository<DataSource> implements DataSourceRepository {
	public DataSourceRepositoryImpl() { setClazz(DataSource.class); }

	public DataSourceRepositoryImpl(EntityManager entityManager) {
		this();
		setEntityManager(entityManager);
	}
}
