package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.ejb.SessionContext;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.VoltageTrans;
import kz.kegoc.bln.entity.dict.translate.VoltageTransTranslate;
import kz.kegoc.bln.filter.AbstractFilter;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.VoltageTransService;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashMap;

@Stateless
public class VoltageTransFilterImpl extends AbstractFilter<VoltageTrans> implements Filter<VoltageTrans> {
    public VoltageTrans filter(VoltageTrans entity, SessionContext context) {
        return translate(prepare(entity, context), context);
    }

    private VoltageTrans prepare(VoltageTrans entity, SessionContext context) {
        if (entity.getId()!=null) {
            VoltageTrans curEntity = service.findById(entity.getId(), null);

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

    private VoltageTrans translate(VoltageTrans entity, SessionContext context) {
        Lang lang = entity.getLang()!=null ? entity.getLang() : defLang;

        VoltageTransTranslate translate = entity.getTranslations().getOrDefault(lang, new VoltageTransTranslate());
        translate = addUpdateInfo(translate, context);
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
