package kz.kegoc.bln.service.dict.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;

import kz.kegoc.bln.entity.dict.MeterType;
import kz.kegoc.bln.repository.common.Repository;
import kz.kegoc.bln.service.common.AbstractEntityService;
import kz.kegoc.bln.service.dict.MeterTypeService;


@Stateless
public class MeterTypeServiceImpl extends AbstractEntityService<MeterType> implements MeterTypeService {
    
	@Inject
    public MeterTypeServiceImpl(Repository<MeterType> repository, Validator validator) {
        super(repository, validator);
    }
}
