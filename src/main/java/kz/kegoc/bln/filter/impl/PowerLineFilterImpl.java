package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.ejb.SessionContext;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.PowerLine;
import kz.kegoc.bln.entity.dict.translate.PowerLineTranslate;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.PowerLineService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.HashMap;

@Stateless
public class PowerLineFilterImpl implements Filter<PowerLine> {
    public PowerLine filter(PowerLine entity, SessionContext context) {
        return translate(prepare(entity, context), context);
    }

    private PowerLine prepare(PowerLine entity, SessionContext context) {
        if (entity.getId()!=null) {
            PowerLine curEntity = service.findById(entity.getId(), null);

            entity.setCreateDate(curEntity.getCreateDate());
            entity.setCreateBy(curEntity.getCreateBy());

            if (entity.getTranslations()==null)
                entity.setTranslations(curEntity.getTranslations());
        }

        if (entity.getTranslations()==null)
            entity.setTranslations(new HashMap<>());

        return entity;
    }

    private PowerLine translate(PowerLine entity, SessionContext context) {
        Lang lang = entity.getLang()!=null ? entity.getLang() : defLang;

        PowerLineTranslate translate = entity.getTranslations().getOrDefault(lang, new PowerLineTranslate());
        if (translate.getId()==null)
            translate.setCreateDate(LocalDateTime.now());
        else
            translate.setLastUpdateDate(LocalDateTime.now());

        translate.setLang(lang);
        translate.setPowerLine(entity);
        translate.setName(entity.getName());
        translate.setShortName(entity.getShortName());
        translate.setPropertyBoundary(entity.getPropertyBoundary());
        entity.getTranslations().put(lang, translate);

        return entity;
    }

    @Inject
    private PowerLineService service;

    @Inject
    private Lang defLang;
}
