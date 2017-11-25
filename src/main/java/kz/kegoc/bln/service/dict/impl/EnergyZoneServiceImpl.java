package kz.kegoc.bln.service.dict.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;

import kz.kegoc.bln.entity.dict.EnergyZone;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.repository.common.Repository;
import kz.kegoc.bln.service.common.AbstractEntityService;
import kz.kegoc.bln.service.dict.EnergyZoneService;
import kz.kegoc.bln.translator.Translator;

@Stateless
public class EnergyZoneServiceImpl extends AbstractEntityService<EnergyZone> implements EnergyZoneService {
    
	@Inject
    public EnergyZoneServiceImpl(Repository<EnergyZone> repository, Validator validator, Filter<EnergyZone> prePersistFilter, Translator<EnergyZone> translator) {
        super(repository, validator, prePersistFilter, translator);
    }
}
