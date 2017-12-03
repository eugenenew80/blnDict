package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.ejb.SessionContext;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.CurrentTrans;
import kz.kegoc.bln.entity.dict.translate.CurrentTransTranslate;
import kz.kegoc.bln.filter.AbstractFilter;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.CurrentTransService;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashMap;

@Stateless
public class CurrentTransFilterImpl extends AbstractFilter<CurrentTrans> implements Filter<CurrentTrans> {
    public CurrentTrans filter(CurrentTrans entity, SessionContext context) {
        return translate(prepare(entity, context), context);
    }

    private CurrentTrans prepare(CurrentTrans entity, SessionContext context) {
        if (entity.getId()!=null) {
            CurrentTrans curEntity = service.findById(entity.getId(), null);

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

    private CurrentTrans translate(CurrentTrans entity, SessionContext context) {
        Lang lang = entity.getLang()!=null ? entity.getLang() : defLang;

        CurrentTransTranslate translate = entity.getTranslations().getOrDefault(lang, new CurrentTransTranslate());
        translate = addUpdateInfo(translate, context);
        translate.setLang(lang);
        translate.setCurrentTrans(entity);
        translate.setName(entity.getName());
        translate.setManufacturer(entity.getManufacturer());
        entity.getTranslations().put(lang, translate);

        return entity;
    }

    @Inject
    private CurrentTransService service;

    @Inject
    private Lang defLang;
}
