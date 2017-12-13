package kz.kegoc.bln.service.dict.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;

import kz.kegoc.bln.entity.dict.SubstationType;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.repository.dict.SubstationTypeRepository;
import kz.kegoc.bln.service.common.AbstractEntityService;
import kz.kegoc.bln.service.dict.SubstationTypeService;
import kz.kegoc.bln.translator.Translator;

@Stateless
public class SubstationTypeServiceImpl extends AbstractEntityService<SubstationType>
        implements SubstationTypeService {
    
	@Inject
    public SubstationTypeServiceImpl(SubstationTypeRepository repository, Validator validator, Filter<SubstationType> prePersistFilter, Translator<SubstationType> translator) {
        super(repository, validator, prePersistFilter, translator);
    }
}
