package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.ejb.SessionContext;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.Contact;
import kz.kegoc.bln.entity.dict.translate.ContactTranslate;
import kz.kegoc.bln.filter.AbstractFilter;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.ContactService;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashMap;

@Stateless
public class ContactFilterImpl extends AbstractFilter<Contact> implements Filter<Contact> {
    public Contact filter(Contact entity, SessionContext context) {
        return translate(prepare(entity, context), context);
    }

    private Contact prepare(Contact entity, SessionContext context) {
        if (entity.getId()!=null) {
            Contact curEntity = service.findById(entity.getId(), context);

            entity.setCreateDate(curEntity.getCreateDate());
            entity.setCreateBy(curEntity.getCreateBy());

            if (entity.getTranslations()==null)
                entity.setTranslations(curEntity.getTranslations());
        }

        if (entity.getTranslations()==null)
            entity.setTranslations(new HashMap<>());

        entity = addUpdateInfo(entity, context);
        return entity;
    }

    private Contact translate(Contact entity, SessionContext context) {
        Lang lang = entity.getLang()!=null ? entity.getLang() : defLang;

        ContactTranslate translate = entity.getTranslations().getOrDefault(lang, new ContactTranslate());
        translate = addUpdateInfo(translate, context);
        translate.setLang(lang);
        translate.setContact(entity);
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
