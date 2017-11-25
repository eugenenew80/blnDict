package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.VoltageTrans;
import kz.kegoc.bln.entity.dict.translate.VoltageTransTranslate;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.VoltageTransService;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashMap;

@Stateless
public class VoltageTransFilterImpl implements Filter<VoltageTrans> {
    public VoltageTrans filter(VoltageTrans entity) {
        if (entity.getId()!=null) {
            VoltageTrans curEntity = service.findById(entity.getId());

            if (entity.getTranslations()==null)
                entity.setTranslations(curEntity.getTranslations());
        }
        return translate(entity);
    }

    private VoltageTrans translate(VoltageTrans entity) {
        Lang lang = entity.getLang()!=null ? entity.getLang() : defLang;
        if (entity.getTranslations()==null)
            entity.setTranslations(new HashMap<>());

        VoltageTransTranslate translate = entity.getTranslations().getOrDefault(lang, new VoltageTransTranslate());
        translate.setLang(lang);
        translate.setVoltageTrans(entity);
        translate.setName(entity.getName());
        translate.setManufacturer(entity.getManufacturer());
        entity.getTranslations().put(lang, translate);

        return entity;
    }

    @Inject
    private VoltageTransService service;

    @Inject
    private Lang defLang;
}
