package kz.kegoc.bln.filter.dict;

import kz.kegoc.bln.ejb.SessionContext;
import kz.kegoc.bln.entity.dict.ContactPhoneNumber;
import kz.kegoc.bln.filter.AbstractFilter;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.ContactPhoneNumberService;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class ContactPhoneNumberFilterImpl extends AbstractFilter<ContactPhoneNumber> implements Filter<ContactPhoneNumber> {
    public ContactPhoneNumber filter(ContactPhoneNumber entity, SessionContext context) {
        return prepare(entity, context);
    }

    private ContactPhoneNumber prepare(ContactPhoneNumber entity, SessionContext context) {
        if (entity.getId()!=null) {
            ContactPhoneNumber curEntity = service.findById(entity.getId(), context);
            entity.setCreateDate(curEntity.getCreateDate());
            entity.setCreateBy(curEntity.getCreateBy());
        }

        entity = addUpdateInfo(entity, context);
        return entity;
    }

    @Inject
    private ContactPhoneNumberService service;
}
