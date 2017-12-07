package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.ejb.SessionContext;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.MeteringPointVoltageTrans;
import kz.kegoc.bln.entity.dict.translate.MeteringPointVoltageTransTranslate;
import kz.kegoc.bln.filter.AbstractFilter;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.MeteringPointVoltageTransService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashMap;

@Stateless
public class MeteringPointVoltageTransFilterImpl extends AbstractFilter<MeteringPointVoltageTrans> implements Filter<MeteringPointVoltageTrans> {
    public MeteringPointVoltageTrans filter(MeteringPointVoltageTrans entity, SessionContext context) {
        return translate(prepare(entity, context), context);
    }

    private MeteringPointVoltageTrans prepare(MeteringPointVoltageTrans entity, SessionContext context) {
        if (entity.getId()!=null) {
            MeteringPointVoltageTrans curEntity = service.findById(entity.getId(), null);
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


    private MeteringPointVoltageTrans translate(MeteringPointVoltageTrans entity, SessionContext context) {
        Lang lang = entity.getLang()!=null ? entity.getLang() : defLang;

        MeteringPointVoltageTransTranslate translate = entity.getTranslations().getOrDefault(lang, new MeteringPointVoltageTransTranslate());
        translate = addUpdateInfo(translate, context);
        translate.setLang(lang);
        translate.setMeteringPointVoltageTrans(entity);
        translate.setName(entity.getName());
        translate.setManufacturer(entity.getManufacturer());
        entity.getTranslations().put(lang, translate);

        return entity;
    }


    @Inject
    private MeteringPointVoltageTransService service;

    @Inject
    private Lang defLang;
}
