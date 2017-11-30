package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.Region;
import kz.kegoc.bln.entity.dict.translate.RegionTranslate;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.RegionService;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashMap;

@Stateless
public class RegionFilterImpl implements Filter<Region> {
    public Region filter(Region entity) {
        if (entity.getId()!=null) {
            Region curEntity = service.findById(entity.getId());

            if (entity.getTranslations()==null)
                entity.setTranslations(curEntity.getTranslations());
        }
        return translate(entity);
    }

    private Region translate(Region entity) {
        Lang lang = entity.getLang()!=null ? entity.getLang() : defLang;
        if (entity.getTranslations()==null)
            entity.setTranslations(new HashMap<>());

        RegionTranslate translate = entity.getTranslations().getOrDefault(lang, new RegionTranslate());
        translate.setLang(lang);
        translate.setRegion(entity);
        translate.setName(entity.getName());
        entity.getTranslations().put(lang, translate);

        return entity;
    }

    @Inject
    private RegionService service;

    @Inject
    private Lang defLang;
}
