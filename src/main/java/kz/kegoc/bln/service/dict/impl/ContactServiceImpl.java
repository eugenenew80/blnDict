package kz.kegoc.bln.service.dict.impl;

import kz.kegoc.bln.entity.dict.Contact;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.repository.common.Repository;
import kz.kegoc.bln.service.common.AbstractEntityService;
import kz.kegoc.bln.service.dict.ContactService;
import kz.kegoc.bln.translator.Translator;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;

@Stateless
public class ContactServiceImpl extends AbstractEntityService<Contact>
        implements ContactService {

	@Inject
    public ContactServiceImpl(Repository<Contact> repository, Validator validator, Filter<Contact> prePersistFilter, Translator<Contact> translator) {
        super(repository, validator, prePersistFilter, translator);
    }
}
