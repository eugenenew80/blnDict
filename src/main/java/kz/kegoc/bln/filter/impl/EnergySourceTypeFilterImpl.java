package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.EnergySourceType;
import kz.kegoc.bln.entity.dict.translate.EnergySourceTypeTranslate;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.EnergySourceTypeService;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashMap;

@Stateless
public class EnergySourceTypeFilterImpl implements Filter<EnergySourceType> {
    public EnergySourceType filter(EnergySourceType entity) {
        if (entity.getId()!=null) {
            EnergySourceType curEntity = service.findById(entity.getId());

            if (entity.getTranslations()==null)
                entity.setTranslations(curEntity.getTranslations());
        }
        return translate(entity);
    }

    private EnergySourceType translate(EnergySourceType entity) {
        Lang lang = entity.getLang()!=null ? entity.getLang() : defLang;
        if (entity.getTranslations()==null)
            entity.setTranslations(new HashMap<>());

        EnergySourceTypeTranslate translate = entity.getTranslations().getOrDefault(lang, new EnergySourceTypeTranslate());
        translate.setLang(lang);
        translate.setEnergySourceType(entity);
        translate.setName(entity.getName());
        translate.setShortName(entity.getShortName());
        entity.getTranslations().put(lang, translate);

        return entity;
    }

    @Inject
    private EnergySourceTypeService service;

    @Inject
    private Lang defLang;
}
