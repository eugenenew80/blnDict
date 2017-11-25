package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.CurrentTransType;
import kz.kegoc.bln.entity.dict.translate.CurrentTransTypeTranslate;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.CurrentTransTypeService;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashMap;

@Stateless
public class CurrentTransTypeFilterImpl implements Filter<CurrentTransType> {
    public CurrentTransType filter(CurrentTransType entity) {
        if (entity.getId()!=null) {
            CurrentTransType curEntity = service.findById(entity.getId());

            if (entity.getTranslations()==null)
                entity.setTranslations(curEntity.getTranslations());
        }
        return translate(entity);
    }

    private CurrentTransType translate(CurrentTransType entity) {
        Lang lang = entity.getLang()!=null ? entity.getLang() : defLang;
        if (entity.getTranslations()==null)
            entity.setTranslations(new HashMap<>());

        CurrentTransTypeTranslate translate = entity.getTranslations().getOrDefault(lang, new CurrentTransTypeTranslate());
        translate.setLang(lang);
        translate.setCurrentTransType(entity);
        translate.setName(entity.getName());
        translate.setManufacturer(entity.getManufacturer());
        entity.getTranslations().put(lang, translate);

        return entity;
    }

    @Inject
    private CurrentTransTypeService service;

    @Inject
    private Lang defLang;
}
