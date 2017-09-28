package kz.kegoc.bln.service.dict.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;

import kz.kegoc.bln.entity.dict.CurrentTransType;
import kz.kegoc.bln.repository.common.Repository;
import kz.kegoc.bln.service.common.AbstractEntityService;
import kz.kegoc.bln.service.dict.CurrentTransTypeService;


@Stateless
public class CurrentTransTypeServiceImpl extends AbstractEntityService<CurrentTransType> implements CurrentTransTypeService {
    
	@Inject
    public CurrentTransTypeServiceImpl(Repository<CurrentTransType> repository, Validator validator) {
        super(repository, validator);
    }
}
