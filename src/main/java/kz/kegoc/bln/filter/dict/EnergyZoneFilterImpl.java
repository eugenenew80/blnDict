package kz.kegoc.bln.filter.dict;

import kz.kegoc.bln.webapi.filters.SessionContext;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.EnergyZone;
import kz.kegoc.bln.entity.dict.translate.EnergyZoneTranslate;
import kz.kegoc.bln.filter.AbstractFilter;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.EnergyZoneService;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashMap;

@Stateless
public class EnergyZoneFilterImpl extends AbstractFilter<EnergyZone> implements Filter<EnergyZone> {
    public EnergyZone filter(EnergyZone entity, SessionContext context) {
        return translate(prepare(entity, context), context);
    }

    private EnergyZone prepare(EnergyZone entity, SessionContext context) {
        if (entity.getId()!=null) {
            EnergyZone curEntity = service.findById(entity.getId(), context);

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

    private EnergyZone translate(EnergyZone entity, SessionContext context) {
        Lang lang = entity.getLang()!=null ? entity.getLang() : defLang;

        EnergyZoneTranslate translate = entity.getTranslations().getOrDefault(lang, new EnergyZoneTranslate());
        translate = addUpdateInfo(translate, context);
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
