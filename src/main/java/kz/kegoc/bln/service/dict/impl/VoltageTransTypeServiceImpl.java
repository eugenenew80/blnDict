package kz.kegoc.bln.service.dict.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;
import kz.kegoc.bln.entity.dict.VoltageTransType;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.repository.dict.VoltageTransTypeRepository;
import kz.kegoc.bln.service.common.AbstractEntityService;
import kz.kegoc.bln.service.dict.VoltageTransTypeService;
import kz.kegoc.bln.translator.Translator;

@Stateless
public class VoltageTransTypeServiceImpl extends AbstractEntityService<VoltageTransType>
        implements VoltageTransTypeService {
    
	@Inject
    public VoltageTransTypeServiceImpl(VoltageTransTypeRepository repository, Validator validator, Filter<VoltageTransType> prePersistFilter, Translator<VoltageTransType> translator) {
        super(repository, validator, prePersistFilter, translator);
    }
}
