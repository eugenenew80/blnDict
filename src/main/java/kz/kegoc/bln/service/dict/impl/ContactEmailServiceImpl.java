package kz.kegoc.bln.service.dict.impl;

import kz.kegoc.bln.entity.dict.ContactEmail;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.repository.common.Repository;
import kz.kegoc.bln.service.common.AbstractEntityService;
import kz.kegoc.bln.service.dict.ContactEmailService;
import kz.kegoc.bln.translator.Translator;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;

@Stateless
public class ContactEmailServiceImpl extends AbstractEntityService<ContactEmail>
        implements ContactEmailService {

	@Inject
    public ContactEmailServiceImpl(Repository<ContactEmail> repository, Validator validator, Filter<ContactEmail> prePersistFilter, Translator<ContactEmail> translator) {
        super(repository, validator, prePersistFilter, translator);
    }
}
