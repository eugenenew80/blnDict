package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.PowerLine;
import kz.kegoc.bln.entity.dict.translate.PowerLineTranslate;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.PowerLineService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashMap;

@Stateless
public class PowerLineFilterImpl implements Filter<PowerLine> {
    public PowerLine filter(PowerLine entity) {
        if (entity.getId()!=null) {
            PowerLine curEntity = service.findById(entity.getId());

            if (entity.getTranslations()==null)
                entity.setTranslations(curEntity.getTranslations());
        }
        return translate(entity);
    }

    private PowerLine translate(PowerLine entity) {
        Lang lang = entity.getLang()!=null ? entity.getLang() : defLang;
        if (entity.getTranslations()==null)
            entity.setTranslations(new HashMap<>());

        PowerLineTranslate translate = entity.getTranslations().getOrDefault(lang, new PowerLineTranslate());
        translate.setLang(lang);
        translate.setPowerLine(entity);
        translate.setName(entity.getName());
        translate.setShortName(entity.getShortName());
        entity.getTranslations().put(lang, translate);

        return entity;
    }

    @Inject
    private PowerLineService service;

    @Inject
    private Lang defLang;
}
