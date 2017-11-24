package kz.kegoc.bln.service.dict.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;

import kz.kegoc.bln.entity.dict.CurrentTrans;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.repository.common.Repository;
import kz.kegoc.bln.service.common.AbstractEntityService;
import kz.kegoc.bln.service.dict.CurrentTransService;


@Stateless
public class CurrentTransServiceImpl extends AbstractEntityService<CurrentTrans>
        implements CurrentTransService {
    
	@Inject
    public CurrentTransServiceImpl(Repository<CurrentTrans> repository, Validator validator, Filter<CurrentTrans> filter) {
        super(repository, validator, filter);
    }
}
