package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.PowerLineType;
import kz.kegoc.bln.entity.dict.translate.PowerLineTypeTranslate;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.PowerLineTypeService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashMap;

@Stateless
public class PowerLineTypeFilterImpl implements Filter<PowerLineType> {
    public PowerLineType filter(PowerLineType entity) {
        if (entity.getId()!=null) {
            PowerLineType curEntity = service.findById(entity.getId());

            if (entity.getTranslations()==null)
                entity.setTranslations(curEntity.getTranslations());
        }
        return translate(entity);
    }

    private PowerLineType translate(PowerLineType entity) {
        Lang lang = entity.getLang()!=null ? entity.getLang() : defLang;
        if (entity.getTranslations()==null)
            entity.setTranslations(new HashMap<>());

        PowerLineTypeTranslate translate = entity.getTranslations().getOrDefault(lang, new PowerLineTypeTranslate());
        translate.setLang(lang);
        translate.setPowerLineType(entity);
        translate.setName(entity.getName());
        entity.getTranslations().put(lang, translate);

        return entity;
    }

    @Inject
    private PowerLineTypeService service;

    @Inject
    private Lang defLang;
}
