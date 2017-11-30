package kz.kegoc.bln.service.dict.impl;

import kz.kegoc.bln.entity.dict.Reactor;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.repository.common.Repository;
import kz.kegoc.bln.service.common.AbstractEntityService;
import kz.kegoc.bln.service.dict.ReactorService;
import kz.kegoc.bln.translator.Translator;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;

@Stateless
public class ReactorServiceImpl extends AbstractEntityService<Reactor> implements ReactorService {

	@Inject
    public ReactorServiceImpl(Repository<Reactor> repository, Validator validator, Filter<Reactor> prePersistFilter, Translator<Reactor> translator) {
        super(repository, validator, prePersistFilter, translator);
    }
}
