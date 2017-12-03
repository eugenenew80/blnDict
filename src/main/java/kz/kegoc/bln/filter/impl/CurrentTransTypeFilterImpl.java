package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.ejb.SessionContext;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.CurrentTransType;
import kz.kegoc.bln.entity.dict.translate.CurrentTransTypeTranslate;
import kz.kegoc.bln.filter.AbstractFilter;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.CurrentTransTypeService;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashMap;

@Stateless
public class CurrentTransTypeFilterImpl extends AbstractFilter<CurrentTransType> implements Filter<CurrentTransType> {
    public CurrentTransType filter(CurrentTransType entity, SessionContext context) {
        return translate(prepare(entity, context), context);
    }

    private CurrentTransType prepare(CurrentTransType entity, SessionContext context) {
        if (entity.getId()!=null) {
            CurrentTransType curEntity = service.findById(entity.getId(), null);

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

    private CurrentTransType translate(CurrentTransType entity, SessionContext context) {
        Lang lang = entity.getLang()!=null ? entity.getLang() : defLang;

        CurrentTransTypeTranslate translate = entity.getTranslations().getOrDefault(lang, new CurrentTransTypeTranslate());
        translate = addUpdateInfo(translate, context);
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
