package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.ejb.SessionContext;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.EnergyZone;
import kz.kegoc.bln.entity.dict.translate.EnergyZoneTranslate;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.EnergyZoneService;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.HashMap;

@Stateless
public class EnergyZoneFilterImpl implements Filter<EnergyZone> {
    public EnergyZone filter(EnergyZone entity, SessionContext context) {
        return translate(prepare(entity));
    }

    private EnergyZone prepare(EnergyZone entity) {
        if (entity.getId()!=null) {
            EnergyZone curEntity = service.findById(entity.getId(), null);

            entity.setCreateDate(curEntity.getCreateDate());
            entity.setCreateBy(curEntity.getCreateBy());

            if (entity.getTranslations()==null)
                entity.setTranslations(curEntity.getTranslations());
        }

        if (entity.getTranslations()==null)
            entity.setTranslations(new HashMap<>());

        return entity;
    }

    private EnergyZone translate(EnergyZone entity) {
        Lang lang = entity.getLang()!=null ? entity.getLang() : defLang;

        EnergyZoneTranslate translate = entity.getTranslations().getOrDefault(lang, new EnergyZoneTranslate());
        if (translate.getId()==null)
            translate.setCreateDate(LocalDateTime.now());
        else
            translate.setLastUpdateDate(LocalDateTime.now());

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
