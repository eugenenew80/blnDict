package kz.kegoc.bln.service.dict.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;

import kz.kegoc.bln.entity.dict.EnergySource;
import kz.kegoc.bln.repository.common.Repository;
import kz.kegoc.bln.service.common.AbstractEntityService;
import kz.kegoc.bln.service.dict.EnergySourceService;


@Stateless
public class EnergySourceServiceImpl extends AbstractEntityService<EnergySource>
        implements EnergySourceService {
    
	@Inject
    public EnergySourceServiceImpl(Repository<EnergySource> repository, Validator validator) {
        super(repository, validator);
    }
}
