package kz.kegoc.bln.service.dict.impl;

import kz.kegoc.bln.entity.dict.BusinessPartner;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.repository.dict.BusinessPartnerRepository;
import kz.kegoc.bln.service.common.AbstractEntityService;
import kz.kegoc.bln.service.dict.BusinessPartnerService;
import kz.kegoc.bln.translator.Translator;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;

@Stateless
public class BusinessPartnerServiceImpl extends AbstractEntityService<BusinessPartner>
        implements BusinessPartnerService {

	@Inject
    public BusinessPartnerServiceImpl(BusinessPartnerRepository repository, Validator validator, Filter<BusinessPartner> prePersistFilter, Translator<BusinessPartner> translator) {
        super(repository, validator, prePersistFilter, translator);
    }
}
