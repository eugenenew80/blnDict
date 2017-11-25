package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.EnergyZone;
import kz.kegoc.bln.entity.dict.translate.EnergyZoneTranslate;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.EnergyZoneService;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashMap;

@Stateless
public class EnergyZoneFilterImpl implements Filter<EnergyZone> {
    public EnergyZone filter(EnergyZone entity) {
        if (entity.getId()!=null) {
            EnergyZone curEntity = service.findById(entity.getId());

            if (entity.getTranslations()==null)
                entity.setTranslations(curEntity.getTranslations());
        }
        return translate(entity);
    }

    private EnergyZone translate(EnergyZone entity) {
        Lang lang = entity.getLang()!=null ? entity.getLang() : defLang;
        if (entity.getTranslations()==null)
            entity.setTranslations(new HashMap<>());

        EnergyZoneTranslate translate = entity.getTranslations().getOrDefault(lang, new EnergyZoneTranslate());
        translate.setLang(lang);
        translate.setEnergyZone(entity);
        translate.setName(entity.getName());
        entity.getTranslations().put(lang, translate);

        return entity;
    }

    @Inject
    private EnergyZoneService service;

    @Inject
    private Lang defLang;
}
