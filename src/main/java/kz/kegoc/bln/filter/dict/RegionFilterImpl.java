package kz.kegoc.bln.filter.dict;

import kz.kegoc.bln.ejb.SessionContext;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.Region;
import kz.kegoc.bln.entity.dict.translate.RegionTranslate;
import kz.kegoc.bln.filter.AbstractFilter;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.RegionService;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashMap;

@Stateless
public class RegionFilterImpl extends AbstractFilter<Region> implements Filter<Region> {
    public Region filter(Region entity, SessionContext context) {
        return translate(prepare(entity, context), context);
    }

    private Region prepare(Region entity, SessionContext context) {
        if (entity.getId()!=null) {
            Region curEntity = service.findById(entity.getId(), null);

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

    private Region translate(Region entity, SessionContext context) {
        Lang lang = entity.getLang()!=null ? entity.getLang() : defLang;

        RegionTranslate translate = entity.getTranslations().getOrDefault(lang, new RegionTranslate());
        translate = addUpdateInfo(translate, context);
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
