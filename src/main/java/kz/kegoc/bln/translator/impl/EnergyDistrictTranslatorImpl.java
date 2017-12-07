package kz.kegoc.bln.translator.impl;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.EnergyDistrict;
import kz.kegoc.bln.entity.dict.translate.EnergyDistrictTranslate;
import kz.kegoc.bln.translator.Translator;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class EnergyDistrictTranslatorImpl implements Translator<EnergyDistrict> {
    public EnergyDistrict translate(EnergyDistrict entity, Lang lang) {
        entity.setLang(lang);

        if (entity.getTranslations()==null)
            return entity;

        EnergyDistrictTranslate translate = entity.getTranslations().get(lang);
        if (translate==null)
            translate = entity.getTranslations().get(defLang);

        if (translate!=null) {
            entity.setName(translate.getName());
            entity.setShortName(translate.getShortName());
        }

        return entity;
    }

    @Inject
    private Lang defLang;
}
