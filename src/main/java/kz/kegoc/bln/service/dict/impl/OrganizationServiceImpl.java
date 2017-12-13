package kz.kegoc.bln.service.dict.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;
import kz.kegoc.bln.entity.dict.Organization;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.repository.dict.OrganizationRepository;
import kz.kegoc.bln.service.common.AbstractEntityService;
import kz.kegoc.bln.service.dict.OrganizationService;
import kz.kegoc.bln.translator.Translator;

@Stateless
public class OrganizationServiceImpl extends AbstractEntityService<Organization>
        implements OrganizationService {
    
	@Inject
    public OrganizationServiceImpl(OrganizationRepository repository, Validator validator, Filter<Organization> prePersistFilter, Translator<Organization> translator) {
        super(repository, validator, prePersistFilter, translator);
    }
}
