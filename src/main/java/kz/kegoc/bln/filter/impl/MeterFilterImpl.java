package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.Meter;
import kz.kegoc.bln.entity.dict.translate.MeterTranslate;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.MeterService;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashMap;

@Stateless
public class MeterFilterImpl implements Filter<Meter> {
    public Meter filter(Meter entity) {
        if (entity.getId()!=null) {
            Meter curEntity = meterService.findById(entity.getId());

            if (entity.getTranslations()==null)
                entity.setTranslations(curEntity.getTranslations());
        }
        return translate(entity);
    }

    private Meter translate(Meter entity) {
        Lang lang = entity.getLang()!=null ? entity.getLang() : defLang;
        if (entity.getTranslations()==null)
            entity.setTranslations(new HashMap<>());

        MeterTranslate translate = entity.getTranslations().getOrDefault(lang, new MeterTranslate());
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
