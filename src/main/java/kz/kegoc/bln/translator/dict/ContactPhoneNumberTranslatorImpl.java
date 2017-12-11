package kz.kegoc.bln.translator.dict;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.ContactPhoneNumber;
import kz.kegoc.bln.translator.Translator;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class ContactPhoneNumberTranslatorImpl implements Translator<ContactPhoneNumber> {
    public ContactPhoneNumber translate(ContactPhoneNumber entity, Lang lang) {
        entity.setLang(lang);
        return entity;
    }

    @Inject
    private Lang defLang;
}
