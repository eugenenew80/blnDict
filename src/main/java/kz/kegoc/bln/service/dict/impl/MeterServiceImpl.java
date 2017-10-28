package kz.kegoc.bln.service.dict.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;

import kz.kegoc.bln.entity.dict.Meter;
import kz.kegoc.bln.repository.common.Repository;
import kz.kegoc.bln.service.common.AbstractEntityService;
import kz.kegoc.bln.service.dict.MeterService;


@Stateless
public class MeterServiceImpl extends AbstractEntityService<Meter>
        implements MeterService {
    
	@Inject
    public MeterServiceImpl(Repository<Meter> repository, Validator validator) {
        super(repository, validator);
    }
}
