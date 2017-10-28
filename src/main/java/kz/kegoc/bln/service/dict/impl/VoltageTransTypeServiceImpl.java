package kz.kegoc.bln.service.dict.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;

import kz.kegoc.bln.entity.dict.VoltageTransType;
import kz.kegoc.bln.repository.common.Repository;
import kz.kegoc.bln.service.common.AbstractEntityService;
import kz.kegoc.bln.service.dict.VoltageTransTypeService;


@Stateless
public class VoltageTransTypeServiceImpl extends AbstractEntityService<VoltageTransType>
        implements VoltageTransTypeService {
    
	@Inject
    public VoltageTransTypeServiceImpl(Repository<VoltageTransType> repository, Validator validator) {
        super(repository, validator);
    }
}
