package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.entity.dict.ContactPhoneNumber;
import kz.kegoc.bln.filter.Filter;

import javax.ejb.Stateless;

@Stateless
public class ContactPhoneNumberFilterImpl implements Filter<ContactPhoneNumber> {
    public ContactPhoneNumber filter(ContactPhoneNumber entity) {
        return entity;
    }
}
