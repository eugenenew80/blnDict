package kz.kegoc.bln.service.dict.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;
import kz.kegoc.bln.entity.dict.Unit;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.repository.dict.UnitRepository;
import kz.kegoc.bln.service.common.AbstractEntityService;
import kz.kegoc.bln.service.dict.UnitService;
import kz.kegoc.bln.translator.Translator;

@Stateless
public class UnitServiceImpl extends AbstractEntityService<Unit>
        implements UnitService {
    
	@Inject
    public UnitServiceImpl(UnitRepository repository, Validator validator, Filter<Unit> prePersistFilter, Translator<Unit> translator) {
        super(repository, validator, prePersistFilter, translator);
    }
}
