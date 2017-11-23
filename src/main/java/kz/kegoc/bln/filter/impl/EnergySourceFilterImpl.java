package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.EnergySource;
import kz.kegoc.bln.entity.dict.translate.EnergySourceTranslate;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.EnergySourceService;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashMap;

@Stateless
public class EnergySourceFilterImpl implements Filter<EnergySource> {
    public EnergySource filter(EnergySource entity) {
        if (entity.getId()!=null) {
            EnergySource curEntity = energySourceService.findById(entity.getId());

            if (entity.getTranslations()==null)
                entity.setTranslations(curEntity.getTranslations());
        }
        return translate(entity);
    }

    private EnergySource translate(EnergySource entity) {
        Lang lang = entity.getLang()!=null ? entity.getLang() : defLang;
        if (entity.getTranslations()==null)
            entity.setTranslations(new HashMap<>());

        EnergySourceTranslate translate = entity.getTranslations().getOrDefault(lang, new EnergySourceTranslate());
        translate.setLang(lang);
        translate.setEnergySource(entity);
        translate.setName(entity.getName());
        translate.setShortName(entity.getShortName());
        translate.setAddress(entity.getAddress());
        entity.getTranslations().put(lang, translate);

        return entity;
    }

    @Inject
    private EnergySourceService energySourceService;

    @Inject
    private Lang defLang;
}
