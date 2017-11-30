package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.entity.dict.PhoneNumber;
import kz.kegoc.bln.filter.Filter;

import javax.ejb.Stateless;

@Stateless
public class PhoneNumberFilterImpl implements Filter<PhoneNumber> {
    public PhoneNumber filter(PhoneNumber entity) {
        return translate(entity);
    }

    private PhoneNumber translate(PhoneNumber entity) {
        return entity;
    }
}
