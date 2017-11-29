package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.Contact;
import kz.kegoc.bln.entity.dict.translate.ContactTranslate;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.ContactService;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashMap;

@Stateless
public class ContactFilterImpl implements Filter<Contact> {
    public Contact filter(Contact entity) {
        if (entity.getId()!=null) {
            Contact curEntity = service.findById(entity.getId());

            if (entity.getTranslations()==null)
                entity.setTranslations(curEntity.getTranslations());
        }
        return translate(entity);
    }

    private Contact translate(Contact entity) {
        Lang lang = entity.getLang()!=null ? entity.getLang() : defLang;
        if (entity.getTranslations()==null)
            entity.setTranslations(new HashMap<>());

        ContactTranslate translate = entity.getTranslations().getOrDefault(lang, new ContactTranslate());
        translate.setLang(lang);
        translate.setContact(entity);
        translate.setContactType(entity.getContactType());
        translate.setPost(entity.getPost());
        translate.setDescription(entity.getDescription());
        entity.getTranslations().put(lang, translate);

        return entity;
    }

    @Inject
    private ContactService service;

    @Inject
    private Lang defLang;
}
