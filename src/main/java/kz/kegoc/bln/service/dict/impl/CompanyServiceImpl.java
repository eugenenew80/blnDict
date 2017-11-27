package kz.kegoc.bln.service.dict.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;

import kz.kegoc.bln.entity.dict.Organization;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.repository.common.Repository;
import kz.kegoc.bln.service.common.AbstractEntityService;
import kz.kegoc.bln.service.dict.CompanyService;
import kz.kegoc.bln.translator.Translator;


@Stateless
public class CompanyServiceImpl extends AbstractEntityService<Organization>
        implements CompanyService {
    
	@Inject
    public CompanyServiceImpl(Repository<Organization> repository, Validator validator, Filter<Organization> prePersistFilter, Translator<Organization> translator) {
        super(repository, validator, prePersistFilter, translator);
    }
}
