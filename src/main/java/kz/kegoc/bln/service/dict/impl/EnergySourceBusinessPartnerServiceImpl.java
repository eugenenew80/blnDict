package kz.kegoc.bln.service.dict.impl;

import kz.kegoc.bln.entity.dict.EnergySourceBusinessPartner;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.repository.common.Repository;
import kz.kegoc.bln.service.common.AbstractEntityService;
import kz.kegoc.bln.service.dict.EnergySourceBusinessPartnerService;
import kz.kegoc.bln.translator.Translator;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;

@Stateless
public class EnergySourceBusinessPartnerServiceImpl extends AbstractEntityService<EnergySourceBusinessPartner>
        implements EnergySourceBusinessPartnerService {

	@Inject
    public EnergySourceBusinessPartnerServiceImpl(Repository<EnergySourceBusinessPartner> repository, Validator validator, Filter<EnergySourceBusinessPartner> prePersistFilter, Translator<EnergySourceBusinessPartner> translator) {
        super(repository, validator, prePersistFilter, translator);
    }
}
