package kz.kegoc.bln.service.dict.impl;

import kz.kegoc.bln.entity.dict.ContactPhoneNumber;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.repository.common.Repository;
import kz.kegoc.bln.service.common.AbstractEntityService;
import kz.kegoc.bln.service.dict.ContactPhoneNumberService;
import kz.kegoc.bln.translator.Translator;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;

@Stateless
public class ContactPhoneNumberServiceImpl extends AbstractEntityService<ContactPhoneNumber> implements ContactPhoneNumberService {
	@Inject
    public ContactPhoneNumberServiceImpl(Repository<ContactPhoneNumber> repository, Validator validator, Filter<ContactPhoneNumber> prePersistFilter, Translator<ContactPhoneNumber> translator) {
        super(repository, validator, prePersistFilter, translator);
    }
}
