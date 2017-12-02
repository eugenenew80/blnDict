package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict. PostAddress;
import kz.kegoc.bln.entity.dict.translate. PostAddressTranslate;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict. PostAddressService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashMap;

@Stateless
public class PostAddressFilterImpl implements Filter< PostAddress> {
    public  PostAddress filter(PostAddress entity) {
        return translate(prepare(entity));
    }

    private PostAddress prepare(PostAddress entity) {
        if (entity.getId()!=null) {
            PostAddress curEntity = service.findById(entity.getId());

            entity.setCreateDate(curEntity.getCreateDate());
            entity.setCreateBy(curEntity.getCreateBy());

            if (entity.getTranslations()==null)
                entity.setTranslations(curEntity.getTranslations());
        }

        if (entity.getTranslations()==null)
            entity.setTranslations(new HashMap<>());

        return entity;
    }

    private PostAddress translate(PostAddress entity) {
        Lang lang = entity.getLang()!=null ? entity.getLang() : defLang;

        PostAddressTranslate translate = entity.getTranslations().getOrDefault(lang, new  PostAddressTranslate());
        translate.setLang(lang);
        translate.setPostAddress(entity);
        translate.setName(entity.getName());
        translate.setLocality(entity.getLocality());
        translate.setStreet(entity.getStreet());
        entity.getTranslations().put(lang, translate);

        return entity;
    }

    @Inject
    private  PostAddressService service;

    @Inject
    private Lang defLang;
}
