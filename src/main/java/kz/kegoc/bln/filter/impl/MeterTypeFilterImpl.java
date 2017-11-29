package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.MeterType;
import kz.kegoc.bln.entity.dict.translate.MeterTypeTranslate;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.MeterTypeService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashMap;

@Stateless
public class MeterTypeFilterImpl implements Filter<MeterType> {
    public MeterType filter(MeterType entity) {
        if (entity.getId()!=null) {
            MeterType curEntity = service.findById(entity.getId());

            if (entity.getTranslations()==null)
                entity.setTranslations(curEntity.getTranslations());
        }
        return translate(entity);
    }

    private MeterType translate(MeterType entity) {
        Lang lang = entity.getLang()!=null ? entity.getLang() : defLang;
        if (entity.getTranslations()==null)
            entity.setTranslations(new HashMap<>());

        MeterTypeTranslate translate = entity.getTranslations().getOrDefault(lang, new MeterTypeTranslate());
        translate.setLang(lang);
        translate.setMeterType(entity);
        translate.setName(entity.getName());
        entity.getTranslations().put(lang, translate);

        return entity;
    }

    @Inject
    private MeterTypeService service;

    @Inject
    private Lang defLang;
}
