package kz.kegoc.bln.service.dict.impl;

import kz.kegoc.bln.entity.dict.MeteringPointCurrentTrans;
import kz.kegoc.bln.repository.common.Repository;
import kz.kegoc.bln.service.common.AbstractEntityService;
import kz.kegoc.bln.service.dict.MeteringPointCurrentTransService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;

@Stateless
public class MeteringPointCurrentTransServiceImpl extends AbstractEntityService<MeteringPointCurrentTrans>
        implements MeteringPointCurrentTransService {

	@Inject
    public MeteringPointCurrentTransServiceImpl(Repository<MeteringPointCurrentTrans> repository, Validator validator) {
        super(repository, validator);
    }
}
