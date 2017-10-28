package kz.kegoc.bln.service.dict.impl;

import kz.kegoc.bln.entity.dict.SubstationCompany;
import kz.kegoc.bln.repository.common.Repository;
import kz.kegoc.bln.service.common.AbstractEntityService;
import kz.kegoc.bln.service.dict.SubstationCompanyService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;


@Stateless
public class SubstationCompanyServiceImpl extends AbstractEntityService<SubstationCompany>
        implements SubstationCompanyService {

	@Inject
    public SubstationCompanyServiceImpl(Repository<SubstationCompany> repository, Validator validator) {
        super(repository, validator);
    }
}
