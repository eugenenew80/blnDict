package kz.kegoc.bln.service.dict.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;

import kz.kegoc.bln.entity.dict.SubstationType;
import kz.kegoc.bln.repository.common.Repository;
import kz.kegoc.bln.service.common.AbstractEntityService;
import kz.kegoc.bln.service.dict.SubstationTypeService;


@Stateless
public class SubstationTypeServiceImpl extends AbstractEntityService<SubstationType> implements SubstationTypeService {
    
	@Inject
    public SubstationTypeServiceImpl(Repository<SubstationType> repository, Validator validator) {
        super(repository, validator);
    }
}
