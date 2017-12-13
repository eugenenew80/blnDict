package kz.kegoc.bln.service.dict.impl;

import kz.kegoc.bln.service.common.AbstractEntityServiceOrg;
import kz.kegoc.bln.webapi.filters.SessionContext;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.Reactor;
import kz.kegoc.bln.exception.RepositoryNotFoundException;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.repository.dict.ReactorRepository;
import kz.kegoc.bln.service.common.AbstractEntityService;
import kz.kegoc.bln.service.dict.ReactorService;
import kz.kegoc.bln.translator.Translator;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class ReactorServiceImpl extends AbstractEntityServiceOrg<Reactor> implements ReactorService {

	@Inject
    public ReactorServiceImpl(ReactorRepository repository, Validator validator, Filter<Reactor> prePersistFilter, Translator<Reactor> translator) {
        super(repository, validator, prePersistFilter, translator);
    }
}
