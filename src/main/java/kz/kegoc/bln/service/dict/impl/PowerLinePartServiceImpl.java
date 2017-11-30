package kz.kegoc.bln.service.dict.impl;

import kz.kegoc.bln.entity.dict.PowerLinePart;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.repository.common.Repository;
import kz.kegoc.bln.service.common.AbstractEntityService;
import kz.kegoc.bln.service.dict.PowerLinePartService;
import kz.kegoc.bln.translator.Translator;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;

@Stateless
public class PowerLinePartServiceImpl extends AbstractEntityService<PowerLinePart> implements PowerLinePartService {

	@Inject
    public PowerLinePartServiceImpl(Repository<PowerLinePart> repository, Validator validator, Filter<PowerLinePart> prePersistFilter, Translator<PowerLinePart> translator) {
        super(repository, validator, prePersistFilter, translator);
    }
}
