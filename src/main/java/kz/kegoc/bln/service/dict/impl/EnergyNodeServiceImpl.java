package kz.kegoc.bln.service.dict.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;

import kz.kegoc.bln.entity.dict.EnergyNode;
import kz.kegoc.bln.repository.common.Repository;
import kz.kegoc.bln.service.common.AbstractEntityService;
import kz.kegoc.bln.service.dict.EnergyNodeService;


@Stateless
public class EnergyNodeServiceImpl extends AbstractEntityService<EnergyNode>
        implements EnergyNodeService {
    
	@Inject
    public EnergyNodeServiceImpl(Repository<EnergyNode> repository, Validator validator) {
        super(repository, validator);
    }
}
