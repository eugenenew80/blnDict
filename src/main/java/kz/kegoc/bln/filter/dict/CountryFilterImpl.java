package kz.kegoc.bln.filter.dict;

import kz.kegoc.bln.webapi.filters.SessionContext;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.Country;
import kz.kegoc.bln.entity.dict.translate.CountryTranslate;
import kz.kegoc.bln.filter.AbstractFilter;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.CountryService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashMap;

@Stateless
public class CountryFilterImpl extends AbstractFilter<Country> implements Filter<Country> {
    public Country filter(Country entity, SessionContext context) {
        return translate(prepare(entity, context), context);
    }

    private Country prepare(Country entity, SessionContext context) {
        if (entity.getId()!=null) {
            Country curEntity = service.findById(entity.getId(), context);

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

    private Country translate(Country entity, SessionContext context) {
        Lang lang = entity.getLang()!=null ? entity.getLang() : defLang;

        CountryTranslate translate = entity.getTranslations().getOrDefault(lang, new CountryTranslate());
        translate = addUpdateInfo(translate, context);
        translate.setLang(lang);
        translate.setCountry(entity);
        translate.setName(entity.getName());
        entity.getTranslations().put(lang, translate);

        return entity;
    }

    @Inject
    private CountryService service;

    @Inject
    private Lang defLang;
}
