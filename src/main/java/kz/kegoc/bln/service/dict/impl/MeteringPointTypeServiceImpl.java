package kz.kegoc.bln.service.dict.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;

import kz.kegoc.bln.entity.dict.MeteringPointType;
import kz.kegoc.bln.repository.common.Repository;
import kz.kegoc.bln.service.common.AbstractEntityService;
import kz.kegoc.bln.service.dict.MeteringPointTypeService;


@Stateless
public class MeteringPointTypeServiceImpl extends AbstractEntityService<MeteringPointType>
        implements MeteringPointTypeService {
    
	@Inject
    public MeteringPointTypeServiceImpl(Repository<MeteringPointType> repository, Validator validator) {
        super(repository, validator);
    }
}
