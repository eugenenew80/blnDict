package kz.kegoc.bln.service.dict.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;

import kz.kegoc.bln.entity.dict.MeteringType;
import kz.kegoc.bln.repository.common.Repository;
import kz.kegoc.bln.service.common.AbstractEntityService;
import kz.kegoc.bln.service.dict.MeteringTypeService;


@Stateless
public class MeteringTypeServiceImpl extends AbstractEntityService<MeteringType> implements MeteringTypeService {
    
	@Inject
    public MeteringTypeServiceImpl(Repository<MeteringType> repository, Validator validator) {
        super(repository, validator);
    }
}
