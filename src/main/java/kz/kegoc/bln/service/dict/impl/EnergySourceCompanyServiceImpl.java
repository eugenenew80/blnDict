package kz.kegoc.bln.service.dict.impl;

import kz.kegoc.bln.entity.dict.EnergySourceBusinessPartner;
import kz.kegoc.bln.repository.common.Repository;
import kz.kegoc.bln.service.common.AbstractEntityService;
import kz.kegoc.bln.service.dict.EnergySourceCompanyService;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;

@Stateless
public class EnergySourceCompanyServiceImpl extends AbstractEntityService<EnergySourceBusinessPartner>
        implements EnergySourceCompanyService {

	@Inject
    public EnergySourceCompanyServiceImpl(Repository<EnergySourceBusinessPartner> repository, Validator validator) {
        super(repository, validator);
    }
}
