package kz.kegoc.bln.service.dict.impl;

import kz.kegoc.bln.entity.dict.PowerLineType;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.repository.dict.PowerLineTypeRepository;
import kz.kegoc.bln.service.common.AbstractEntityService;
import kz.kegoc.bln.service.dict.PowerLineTypeService;
import kz.kegoc.bln.translator.Translator;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;

@Stateless
public class PowerLineTypeServiceImpl extends AbstractEntityService<PowerLineType> implements PowerLineTypeService {

	@Inject
    public PowerLineTypeServiceImpl(PowerLineTypeRepository repository, Validator validator, Filter<PowerLineType> prePersistFilter, Translator<PowerLineType> translator) {
        super(repository, validator, prePersistFilter, translator);
    }
}
