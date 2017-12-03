package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.ejb.SessionContext;
import kz.kegoc.bln.entity.dict.ContactPhoneNumber;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.ContactPhoneNumberService;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class ContactPhoneNumberFilterImpl implements Filter<ContactPhoneNumber> {
    public ContactPhoneNumber filter(ContactPhoneNumber entity, SessionContext context) {
        return prepare(entity);
    }

    private ContactPhoneNumber prepare(ContactPhoneNumber entity) {
        if (entity.getId()!=null) {
            ContactPhoneNumber curEntity = service.findById(entity.getId(), null);
            entity.setCreateDate(curEntity.getCreateDate());
            entity.setCreateBy(curEntity.getCreateBy());
        }

        return entity;
    }

    @Inject
    private ContactPhoneNumberService service;
}
