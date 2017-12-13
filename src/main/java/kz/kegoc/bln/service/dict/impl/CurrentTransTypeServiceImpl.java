package kz.kegoc.bln.service.dict.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;
import kz.kegoc.bln.entity.dict.CurrentTransType;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.repository.dict.CurrentTransTypeRepository;
import kz.kegoc.bln.service.common.AbstractEntityService;
import kz.kegoc.bln.service.dict.CurrentTransTypeService;
import kz.kegoc.bln.translator.Translator;

@Stateless
public class CurrentTransTypeServiceImpl extends AbstractEntityService<CurrentTransType>
        implements CurrentTransTypeService {
    
	@Inject
    public CurrentTransTypeServiceImpl(CurrentTransTypeRepository repository, Validator validator, Filter<CurrentTransType> prePersistFilter, Translator<CurrentTransType> translator) {
        super(repository, validator, prePersistFilter, translator);
    }
}
