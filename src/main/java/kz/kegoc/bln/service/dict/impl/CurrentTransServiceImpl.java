package kz.kegoc.bln.service.dict.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;

import kz.kegoc.bln.entity.dict.CurrentTrans;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.repository.common.Repository;
import kz.kegoc.bln.service.common.AbstractEntityService;
import kz.kegoc.bln.service.dict.CurrentTransService;
import kz.kegoc.bln.translator.Translator;


@Stateless
public class CurrentTransServiceImpl extends AbstractEntityService<CurrentTrans>
        implements CurrentTransService {
    
	@Inject
    public CurrentTransServiceImpl(Repository<CurrentTrans> repository, Validator validator, Filter<CurrentTrans> prePersistFilter, Translator<CurrentTrans> translator) {
        super(repository, validator, prePersistFilter, translator);
    }
}
