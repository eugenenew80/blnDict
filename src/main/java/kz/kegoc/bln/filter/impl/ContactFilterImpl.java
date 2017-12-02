package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.Contact;
import kz.kegoc.bln.entity.dict.translate.ContactTranslate;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.ContactService;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.HashMap;

@Stateless
public class ContactFilterImpl implements Filter<Contact> {
    public Contact filter(Contact entity) {
        return translate(prepare(entity));
    }

    private Contact prepare(Contact entity) {
        if (entity.getId()!=null) {
            Contact curEntity = service.findById(entity.getId());

            entity.setCreateDate(curEntity.getCreateDate());
            entity.setCreateBy(curEntity.getCreateBy());

            if (entity.getTranslations()==null)
                entity.setTranslations(curEntity.getTranslations());
        }

        if (entity.getTranslations()==null)
            entity.setTranslations(new HashMap<>());

        return entity;
    }

    private Contact translate(Contact entity) {
        Lang lang = entity.getLang()!=null ? entity.getLang() : defLang;

        ContactTranslate translate = entity.getTranslations().getOrDefault(lang, new ContactTranslate());
        if (translate.getId()==null)
            translate.setCreateDate(LocalDateTime.now());
        else
            translate.setLastUpdateDate(LocalDateTime.now());

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
