package kz.kegoc.bln.service.dict.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;
import kz.kegoc.bln.entity.dict.MeteringType;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.repository.dict.MeteringTypeRepository;
import kz.kegoc.bln.service.common.AbstractEntityService;
import kz.kegoc.bln.service.dict.MeteringTypeService;
import kz.kegoc.bln.translator.Translator;

@Stateless
public class MeteringTypeServiceImpl extends AbstractEntityService<MeteringType> implements MeteringTypeService {
    
	@Inject
    public MeteringTypeServiceImpl(MeteringTypeRepository repository, Validator validator, Filter<MeteringType> prePersistFilter, Translator<MeteringType> translator) {
        super(repository, validator, prePersistFilter, translator);
    }
}
