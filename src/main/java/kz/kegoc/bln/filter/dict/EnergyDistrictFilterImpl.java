package kz.kegoc.bln.filter.dict;

import kz.kegoc.bln.webapi.filters.SessionContext;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.EnergyDistrict;
import kz.kegoc.bln.entity.dict.translate.EnergyDistrictTranslate;
import kz.kegoc.bln.filter.AbstractFilter;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.EnergyDistrictService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashMap;

@Stateless
public class EnergyDistrictFilterImpl extends AbstractFilter<EnergyDistrict> implements Filter<EnergyDistrict> {
    public EnergyDistrict filter(EnergyDistrict entity, SessionContext context) {
        return translate(prepare(entity, context), context);
    }

    private EnergyDistrict prepare(EnergyDistrict entity, SessionContext context) {
        if (entity.getId()!=null) {
            EnergyDistrict curEntity = service.findById(entity.getId(), null);

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

    private EnergyDistrict translate(EnergyDistrict entity, SessionContext context) {
        Lang lang = entity.getLang()!=null ? entity.getLang() : defLang;

        EnergyDistrictTranslate translate = entity.getTranslations().getOrDefault(lang, new EnergyDistrictTranslate());
        translate = addUpdateInfo(translate, context);
        translate.setLang(lang);
        translate.setEnergyDistrict(entity);
        translate.setName(entity.getName());
        translate.setShortName(entity.getShortName());
        entity.getTranslations().put(lang, translate);

        return entity;
    }

    @Inject
    private EnergyDistrictService service;

    @Inject
    private Lang defLang;
}
