package kz.kegoc.bln.service.dict.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;

import kz.kegoc.bln.entity.dict.EnergyNode;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.repository.dict.EnergyNodeRepository;
import kz.kegoc.bln.service.common.AbstractEntityService;
import kz.kegoc.bln.service.dict.EnergyNodeService;
import kz.kegoc.bln.translator.Translator;

@Stateless
public class EnergyNodeServiceImpl extends AbstractEntityService<EnergyNode> implements EnergyNodeService {
    
	@Inject
    public EnergyNodeServiceImpl(EnergyNodeRepository repository, Validator validator, Filter<EnergyNode> prePersistFilter, Translator<EnergyNode> translator) {
        super(repository, validator, prePersistFilter, translator);
    }
}
