package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.ejb.SessionContext;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.VoltageTrans;
import kz.kegoc.bln.entity.dict.translate.VoltageTransTranslate;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.VoltageTransService;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.HashMap;

@Stateless
public class VoltageTransFilterImpl implements Filter<VoltageTrans> {
    public VoltageTrans filter(VoltageTrans entity, SessionContext context) {
        return translate(prepare(entity));
    }

    private VoltageTrans prepare(VoltageTrans entity) {
        if (entity.getId()!=null) {
            VoltageTrans curEntity = service.findById(entity.getId(), null);

            entity.setCreateDate(curEntity.getCreateDate());
            entity.setCreateBy(curEntity.getCreateBy());

            if (entity.getTranslations()==null)
                entity.setTranslations(curEntity.getTranslations());
        }

        if (entity.getTranslations()==null)
            entity.setTranslations(new HashMap<>());

        return entity;
    }

    private VoltageTrans translate(VoltageTrans entity) {
        Lang lang = entity.getLang()!=null ? entity.getLang() : defLang;

        VoltageTransTranslate translate = entity.getTranslations().getOrDefault(lang, new VoltageTransTranslate());
        if (translate.getId()==null)
            translate.setCreateDate(LocalDateTime.now());
        else
            translate.setLastUpdateDate(LocalDateTime.now());

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
