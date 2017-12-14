package kz.kegoc.bln.service.dict.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;
import kz.kegoc.bln.entity.dict.Meter;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.repository.dict.MeterRepository;
import kz.kegoc.bln.service.common.AbstractEntityServiceOrg;
import kz.kegoc.bln.service.dict.MeterService;
import kz.kegoc.bln.translator.Translator;

@Stateless
public class MeterServiceImpl extends AbstractEntityServiceOrg<Meter>
        implements MeterService {
    
	@Inject
    public MeterServiceImpl(MeterRepository repository, Validator validator, Filter<Meter> prePersistFilter, Translator<Meter> translator) {
        super(repository, validator, prePersistFilter, translator);
    }
}
