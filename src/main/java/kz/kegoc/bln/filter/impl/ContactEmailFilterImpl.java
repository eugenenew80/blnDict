package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.ContactEmail;
import kz.kegoc.bln.entity.dict.translate.ContactEmailTranslate;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.ContactEmailService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashMap;

@Stateless
public class ContactEmailFilterImpl implements Filter<ContactEmail> {
    public ContactEmail filter(ContactEmail entity) {
        return translate(prepare(entity));
    }

    private ContactEmail prepare(ContactEmail entity) {
        if (entity.getId()!=null) {
            ContactEmail curEntity = service.findById(entity.getId());

            entity.setCreateDate(curEntity.getCreateDate());
            entity.setCreateBy(curEntity.getCreateBy());

            if (entity.getTranslations()==null)
                entity.setTranslations(curEntity.getTranslations());
        }

        if (entity.getTranslations()==null)
            entity.setTranslations(new HashMap<>());

        return entity;
    }

    private ContactEmail translate(ContactEmail entity) {
        Lang lang = entity.getLang()!=null ? entity.getLang() : defLang;

        ContactEmailTranslate translate = entity.getTranslations().getOrDefault(lang, new ContactEmailTranslate());
        translate.setLang(lang);
        translate.setContactEmail(entity);
        translate.setDescription(entity.getDescription());
        entity.getTranslations().put(lang, translate);

        return entity;
    }

    @Inject
    private ContactEmailService service;

    @Inject
    private Lang defLang;
}
