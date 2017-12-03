package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.ejb.SessionContext;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.Meter;
import kz.kegoc.bln.entity.dict.translate.MeterTranslate;
import kz.kegoc.bln.filter.AbstractFilter;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.MeterService;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashMap;

@Stateless
public class MeterFilterImpl extends AbstractFilter<Meter> implements Filter<Meter> {
    public Meter filter(Meter entity, SessionContext context) {
        return translate(prepare(entity, context), context);
    }

    private Meter prepare(Meter entity, SessionContext context) {
        if (entity.getId()!=null) {
            Meter curEntity = meterService.findById(entity.getId(), null);

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

    private Meter translate(Meter entity, SessionContext context) {
        Lang lang = entity.getLang()!=null ? entity.getLang() : defLang;

        MeterTranslate translate = entity.getTranslations().getOrDefault(lang, new MeterTranslate());
        translate = addUpdateInfo(translate, context);
        translate.setLang(lang);
        translate.setMeter(entity);
        translate.setName(entity.getName());
        translate.setManufacturer(entity.getManufacturer());
        entity.getTranslations().put(lang, translate);

        return entity;
    }

    @Inject
    private MeterService meterService;

    @Inject
    private Lang defLang;
}
