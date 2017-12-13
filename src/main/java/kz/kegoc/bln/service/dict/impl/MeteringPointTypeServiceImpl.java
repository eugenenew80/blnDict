package kz.kegoc.bln.service.dict.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;
import kz.kegoc.bln.entity.dict.MeteringPointType;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.repository.dict.MeteringPointTypeRepository;
import kz.kegoc.bln.service.common.AbstractEntityService;
import kz.kegoc.bln.service.dict.MeteringPointTypeService;
import kz.kegoc.bln.translator.Translator;

@Stateless
public class MeteringPointTypeServiceImpl extends AbstractEntityService<MeteringPointType> implements MeteringPointTypeService {
    
	@Inject
    public MeteringPointTypeServiceImpl(MeteringPointTypeRepository repository, Validator validator, Filter<MeteringPointType> prePersistFilter, Translator<MeteringPointType> translator) {
        super(repository, validator, prePersistFilter, translator);
    }
}
