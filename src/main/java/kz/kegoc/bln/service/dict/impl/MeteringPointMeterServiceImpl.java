package kz.kegoc.bln.service.dict.impl;

import kz.kegoc.bln.entity.dict.MeteringPointMeter;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.repository.dict.MeteringPointMeterRepository;
import kz.kegoc.bln.service.common.AbstractEntityService;
import kz.kegoc.bln.service.dict.MeteringPointMeterService;
import kz.kegoc.bln.translator.Translator;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;

@Stateless
public class MeteringPointMeterServiceImpl extends AbstractEntityService<MeteringPointMeter>
        implements MeteringPointMeterService {

	@Inject
    public MeteringPointMeterServiceImpl(MeteringPointMeterRepository repository, Validator validator, Filter<MeteringPointMeter> prePersistFilter, Translator<MeteringPointMeter> translator) {
        super(repository, validator, prePersistFilter, translator);
    }
}
