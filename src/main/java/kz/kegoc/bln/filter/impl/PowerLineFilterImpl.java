package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.ejb.SessionContext;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.PowerLine;
import kz.kegoc.bln.entity.dict.translate.PowerLineTranslate;
import kz.kegoc.bln.filter.AbstractFilter;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.PowerLineService;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashMap;

@Stateless
public class PowerLineFilterImpl extends AbstractFilter<PowerLine> implements Filter<PowerLine> {
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

        entity = addUpdateInfo(entity, context);
        return entity;
    }

    private PowerLine translate(PowerLine entity, SessionContext context) {
        Lang lang = entity.getLang()!=null ? entity.getLang() : defLang;

        PowerLineTranslate translate = entity.getTranslations().getOrDefault(lang, new PowerLineTranslate());
        translate = addUpdateInfo(translate, context);
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
