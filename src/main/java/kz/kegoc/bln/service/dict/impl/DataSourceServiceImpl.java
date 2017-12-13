package kz.kegoc.bln.service.dict.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;
import kz.kegoc.bln.entity.dict.DataSource;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.repository.dict.DataSourceRepository;
import kz.kegoc.bln.service.common.AbstractEntityService;
import kz.kegoc.bln.service.dict.DataSourceService;
import kz.kegoc.bln.translator.Translator;

@Stateless
public class DataSourceServiceImpl extends AbstractEntityService<DataSource>
        implements DataSourceService {
    
	@Inject
    public DataSourceServiceImpl(DataSourceRepository repository, Validator validator, Filter<DataSource> prePersistFilter, Translator<DataSource> translator) {
        super(repository, validator, prePersistFilter, translator);
    }
}
