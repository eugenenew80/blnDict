package kz.kegoc.bln.service.dict.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;

import kz.kegoc.bln.entity.dict.EnergyZone;
import kz.kegoc.bln.repository.common.Repository;
import kz.kegoc.bln.service.common.AbstractEntityService;
import kz.kegoc.bln.service.dict.EnergyZoneService;


@Stateless
public class EnergyZoneServiceImpl extends AbstractEntityService<EnergyZone>
        implements EnergyZoneService {
    
	@Inject
    public EnergyZoneServiceImpl(Repository<EnergyZone> repository, Validator validator) {
        super(repository, validator);
    }
}
