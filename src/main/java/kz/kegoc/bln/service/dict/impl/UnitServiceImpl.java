package kz.kegoc.bln.service.dict.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;

import kz.kegoc.bln.entity.dict.Unit;
import kz.kegoc.bln.repository.common.Repository;
import kz.kegoc.bln.service.common.AbstractEntityService;
import kz.kegoc.bln.service.dict.UnitService;


@Stateless
public class UnitServiceImpl extends AbstractEntityService<Unit> implements UnitService {
    
	@Inject
    public UnitServiceImpl(Repository<Unit> repository, Validator validator) {
        super(repository, validator);
    }
}
