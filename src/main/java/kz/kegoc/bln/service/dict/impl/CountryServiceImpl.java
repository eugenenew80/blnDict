package kz.kegoc.bln.service.dict.impl;

import kz.kegoc.bln.entity.dict.Country;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.repository.dict.CountryRepository;
import kz.kegoc.bln.service.common.AbstractEntityService;
import kz.kegoc.bln.service.dict.CountryService;
import kz.kegoc.bln.translator.Translator;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;

@Stateless
public class CountryServiceImpl extends AbstractEntityService<Country>
        implements CountryService {

	@Inject
    public CountryServiceImpl(CountryRepository repository, Validator validator, Filter<Country> prePersistFilter, Translator<Country> translator) {
        super(repository, validator, prePersistFilter, translator);
    }
}
