package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.ejb.SessionContext;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.CurrentTrans;
import kz.kegoc.bln.entity.dict.translate.CurrentTransTranslate;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.CurrentTransService;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.HashMap;

@Stateless
public class CurrentTransFilterImpl implements Filter<CurrentTrans> {
    public CurrentTrans filter(CurrentTrans entity, SessionContext context) {
        return translate(prepare(entity));
    }

    private CurrentTrans prepare(CurrentTrans entity) {
        if (entity.getId()!=null) {
            CurrentTrans curEntity = currentTransService.findById(entity.getId(), null);

            entity.setCreateDate(curEntity.getCreateDate());
            entity.setCreateBy(curEntity.getCreateBy());

            if (entity.getTranslations()==null)
                entity.setTranslations(curEntity.getTranslations());
        }

        if (entity.getTranslations()==null)
            entity.setTranslations(new HashMap<>());

        return entity;
    }

    private CurrentTrans translate(CurrentTrans entity) {
        Lang lang = entity.getLang()!=null ? entity.getLang() : defLang;

        CurrentTransTranslate translate = entity.getTranslations().getOrDefault(lang, new CurrentTransTranslate());
        if (translate.getId()==null)
            translate.setCreateDate(LocalDateTime.now());
        else
            translate.setLastUpdateDate(LocalDateTime.now());

        translate.setLang(lang);
        translate.setCurrentTrans(entity);
        translate.setName(entity.getName());
        translate.setManufacturer(entity.getManufacturer());
        entity.getTranslations().put(lang, translate);

        return entity;
    }

    @Inject
    private CurrentTransService currentTransService;

    @Inject
    private Lang defLang;
}
