package kz.kegoc.bln.service.dict.impl;

import kz.kegoc.bln.entity.dict.SubstationMeteringPoint;
import kz.kegoc.bln.repository.common.Repository;
import kz.kegoc.bln.service.common.AbstractEntityService;
import kz.kegoc.bln.service.dict.SubstationMeteringPointService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;


@Stateless
public class SubstationMeteringPointServiceImpl extends AbstractEntityService<SubstationMeteringPoint>
        implements SubstationMeteringPointService {

	@Inject
    public SubstationMeteringPointServiceImpl(Repository<SubstationMeteringPoint> repository, Validator validator) {
        super(repository, validator);
    }
}
