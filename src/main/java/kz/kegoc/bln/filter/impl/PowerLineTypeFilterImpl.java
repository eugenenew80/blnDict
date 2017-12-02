package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.PowerLineType;
import kz.kegoc.bln.entity.dict.translate.PowerLineTypeTranslate;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.PowerLineTypeService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.HashMap;

@Stateless
public class PowerLineTypeFilterImpl implements Filter<PowerLineType> {
    public PowerLineType filter(PowerLineType entity) {
        return translate(prepare(entity));
    }

    private PowerLineType prepare(PowerLineType entity) {
        if (entity.getId()!=null) {
            PowerLineType curEntity = service.findById(entity.getId());

            entity.setCreateDate(curEntity.getCreateDate());
            entity.setCreateBy(curEntity.getCreateBy());

            if (entity.getTranslations()==null)
                entity.setTranslations(curEntity.getTranslations());
        }

        if (entity.getTranslations()==null)
            entity.setTranslations(new HashMap<>());

        return entity;
    }

    private PowerLineType translate(PowerLineType entity) {
        Lang lang = entity.getLang()!=null ? entity.getLang() : defLang;

        PowerLineTypeTranslate translate = entity.getTranslations().getOrDefault(lang, new PowerLineTypeTranslate());
        if (translate.getId()==null)
            translate.setCreateDate(LocalDateTime.now());
        else
            translate.setLastUpdateDate(LocalDateTime.now());

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
