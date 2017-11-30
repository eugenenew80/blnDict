package kz.kegoc.bln.service.dict.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;

import kz.kegoc.bln.entity.dict.Region;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.repository.common.Repository;
import kz.kegoc.bln.service.common.AbstractEntityService;
import kz.kegoc.bln.service.dict.RegionService;
import kz.kegoc.bln.translator.Translator;

@Stateless
public class RegionServiceImpl extends AbstractEntityService<Region> implements RegionService {
    
	@Inject
    public RegionServiceImpl(Repository<Region> repository, Validator validator, Filter<Region> prePersistFilter, Translator<Region> translator) {
        super(repository, validator, prePersistFilter, translator);
    }
}
