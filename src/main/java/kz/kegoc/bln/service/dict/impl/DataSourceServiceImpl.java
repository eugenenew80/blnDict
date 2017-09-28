package kz.kegoc.bln.service.dict.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;

import kz.kegoc.bln.entity.dict.DataSource;
import kz.kegoc.bln.repository.common.Repository;
import kz.kegoc.bln.service.common.AbstractEntityService;
import kz.kegoc.bln.service.dict.DataSourceService;


@Stateless
public class DataSourceServiceImpl extends AbstractEntityService<DataSource> implements DataSourceService {
    
	@Inject
    public DataSourceServiceImpl(Repository<DataSource> repository, Validator validator) {
        super(repository, validator);
    }
}
