package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.Country;
import kz.kegoc.bln.entity.dict.translate.CountryTranslate;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.CountryService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.HashMap;

@Stateless
public class CountryFilterImpl implements Filter<Country> {
    public Country filter(Country entity) {
        return translate(prepare(entity));
    }

    private Country prepare(Country entity) {
        if (entity.getId()!=null) {
            Country curEntity = countryService.findById(entity.getId());

            entity.setCreateDate(curEntity.getCreateDate());
            entity.setCreateBy(curEntity.getCreateBy());

            if (entity.getTranslations()==null)
                entity.setTranslations(curEntity.getTranslations());
        }

        if (entity.getTranslations()==null)
            entity.setTranslations(new HashMap<>());

        return entity;
    }

    private Country translate(Country entity) {
        Lang lang = entity.getLang()!=null ? entity.getLang() : defLang;

        CountryTranslate translate = entity.getTranslations().getOrDefault(lang, new CountryTranslate());
        if (translate.getId()==null)
            translate.setCreateDate(LocalDateTime.now());
        else
            translate.setLastUpdateDate(LocalDateTime.now());

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
