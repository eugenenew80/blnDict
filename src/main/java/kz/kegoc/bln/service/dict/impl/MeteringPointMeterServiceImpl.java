package kz.kegoc.bln.service.dict.impl;

import kz.kegoc.bln.entity.dict.MeteringPointMeter;
import kz.kegoc.bln.repository.common.Repository;
import kz.kegoc.bln.service.common.AbstractEntityService;
import kz.kegoc.bln.service.dict.MeteringPointMeterService;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;

@Stateless
public class MeteringPointMeterServiceImpl extends AbstractEntityService<MeteringPointMeter>
        implements MeteringPointMeterService {

	@Inject
    public MeteringPointMeterServiceImpl(Repository<MeteringPointMeter> repository, Validator validator) {
        super(repository, validator);
    }
}
