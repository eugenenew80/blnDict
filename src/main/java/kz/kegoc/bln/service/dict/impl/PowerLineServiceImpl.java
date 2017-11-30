package kz.kegoc.bln.service.dict.impl;

import kz.kegoc.bln.entity.dict.PowerLine;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.repository.common.Repository;
import kz.kegoc.bln.service.common.AbstractEntityService;
import kz.kegoc.bln.service.dict.PowerLineService;
import kz.kegoc.bln.translator.Translator;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;

@Stateless
public class PowerLineServiceImpl extends AbstractEntityService<PowerLine> implements PowerLineService {

	@Inject
    public PowerLineServiceImpl(Repository<PowerLine> repository, Validator validator, Filter<PowerLine> prePersistFilter, Translator<PowerLine> translator) {
        super(repository, validator, prePersistFilter, translator);
    }
}
