package kz.kegoc.bln.service.dict.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;

import kz.kegoc.bln.entity.dict.EnergySourceType;
import kz.kegoc.bln.repository.common.Repository;
import kz.kegoc.bln.service.common.AbstractEntityService;
import kz.kegoc.bln.service.dict.EnergySourceTypeService;


@Stateless
public class EnergySourceTypeServiceImpl extends AbstractEntityService<EnergySourceType>
        implements EnergySourceTypeService {
    
	@Inject
    public EnergySourceTypeServiceImpl(Repository<EnergySourceType> repository, Validator validator) {
        super(repository, validator);
    }
}
