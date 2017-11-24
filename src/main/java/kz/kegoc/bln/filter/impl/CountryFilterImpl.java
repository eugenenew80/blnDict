package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.Country;
import kz.kegoc.bln.entity.dict.translate.CountryTranslate;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.CountryService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashMap;

@Stateless
public class CountryFilterImpl implements Filter<Country> {
    public Country filter(Country entity) {
        if (entity.getId()!=null) {
            Country curEntity = countryService.findById(entity.getId());

            if (entity.getTranslations()==null)
                entity.setTranslations(curEntity.getTranslations());
        }
        return translate(entity);
    }

    private Country translate(Country entity) {
        Lang lang = entity.getLang()!=null ? entity.getLang() : defLang;
        if (entity.getTranslations()==null)
            entity.setTranslations(new HashMap<>());

        CountryTranslate translate = entity.getTranslations().getOrDefault(lang, new CountryTranslate());
        translate.setLang(lang);
        translate.setCountry(entity);
        translate.setName(entity.getName());
        entity.getTranslations().put(lang, translate);

        return entity;
    }

    @Inject
    private CountryService countryService;

    @Inject
    private Lang defLang;
}
