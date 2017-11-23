package kz.kegoc.bln.service.dict.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;
import kz.kegoc.bln.entity.dict.Substation;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.repository.common.Repository;
import kz.kegoc.bln.service.common.AbstractEntityService;
import kz.kegoc.bln.service.dict.SubstationService;


@Stateless
public class SubstationServiceImpl extends AbstractEntityService<Substation>
        implements SubstationService {
    
	@Inject
    public SubstationServiceImpl(Repository<Substation> repository, Validator validator, Filter<Substation> prePersistFilte) {
        super(repository, validator, prePersistFilte);
    }
}
