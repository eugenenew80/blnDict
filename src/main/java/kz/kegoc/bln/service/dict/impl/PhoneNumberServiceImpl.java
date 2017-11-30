package kz.kegoc.bln.service.dict.impl;

import kz.kegoc.bln.entity.dict.PhoneNumber;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.repository.common.Repository;
import kz.kegoc.bln.service.common.AbstractEntityService;
import kz.kegoc.bln.service.dict.PhoneNumberService;
import kz.kegoc.bln.translator.Translator;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;

@Stateless
public class PhoneNumberServiceImpl extends AbstractEntityService<PhoneNumber> implements PhoneNumberService {
	@Inject
    public PhoneNumberServiceImpl(Repository<PhoneNumber> repository, Validator validator, Filter<PhoneNumber> prePersistFilter, Translator<PhoneNumber> translator) {
        super(repository, validator, prePersistFilter, translator);
    }
}
