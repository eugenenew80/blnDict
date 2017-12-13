package kz.kegoc.bln.service.dict.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;

import kz.kegoc.bln.entity.dict.MeterType;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.repository.dict.MeterTypeRepository;
import kz.kegoc.bln.service.common.AbstractEntityService;
import kz.kegoc.bln.service.dict.MeterTypeService;
import kz.kegoc.bln.translator.Translator;

@Stateless
public class MeterTypeServiceImpl extends AbstractEntityService<MeterType>
        implements MeterTypeService {
    
	@Inject
    public MeterTypeServiceImpl(MeterTypeRepository repository, Validator validator, Filter<MeterType> prePersistFilter, Translator<MeterType> translator) {
        super(repository, validator, prePersistFilter, translator);
    }
}
