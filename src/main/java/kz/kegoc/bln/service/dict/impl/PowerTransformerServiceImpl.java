package kz.kegoc.bln.service.dict.impl;

import kz.kegoc.bln.entity.dict.PowerTransformer;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.repository.dict.PowerTransformerRepository;
import kz.kegoc.bln.service.common.AbstractEntityService;
import kz.kegoc.bln.service.dict.PowerTransformerService;
import kz.kegoc.bln.translator.Translator;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;

@Stateless
public class PowerTransformerServiceImpl extends AbstractEntityService<PowerTransformer> implements PowerTransformerService {

	@Inject
    public PowerTransformerServiceImpl(PowerTransformerRepository repository, Validator validator, Filter<PowerTransformer> prePersistFilter, Translator<PowerTransformer> translator) {
        super(repository, validator, prePersistFilter, translator);
    }
}
