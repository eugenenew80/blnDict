package kz.kegoc.bln.service.dict.impl;

import kz.kegoc.bln.entity.dict.EnergyDistrict;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.repository.dict.EnergyDistrictRepository;
import kz.kegoc.bln.service.common.AbstractEntityService;
import kz.kegoc.bln.service.dict.EnergyDistrictService;
import kz.kegoc.bln.translator.Translator;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;

@Stateless
public class EnergyDistrictServiceImpl extends AbstractEntityService<EnergyDistrict> implements EnergyDistrictService {

	@Inject
    public EnergyDistrictServiceImpl(EnergyDistrictRepository repository, Validator validator, Filter<EnergyDistrict> prePersistFilter, Translator<EnergyDistrict> translator) {
        super(repository, validator, prePersistFilter, translator);
    }
}
