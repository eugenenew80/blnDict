package kz.kegoc.bln.service.dict.impl;

import kz.kegoc.bln.entity.dict.BusinessPartner;
import kz.kegoc.bln.repository.common.Repository;
import kz.kegoc.bln.service.common.AbstractEntityService;
import kz.kegoc.bln.service.dict.BusinessPartnerService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;


@Stateless
public class BusinessPartnerServiceImpl extends AbstractEntityService<BusinessPartner>
        implements BusinessPartnerService {

	@Inject
    public BusinessPartnerServiceImpl(Repository<BusinessPartner> repository, Validator validator) {
        super(repository, validator);
    }
}
