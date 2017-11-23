package kz.kegoc.bln.translator.impl;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.EnergySource;
import kz.kegoc.bln.entity.dict.translate.EnergySourceTranslate;
import kz.kegoc.bln.translator.Translator;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class EnergySourceTranslatorImpl implements Translator<EnergySource> {
    public EnergySource translate(EnergySource entity, Lang lang) {
        entity.setLang(lang);

        if (entity.getTranslations()==null)
            return entity;

        EnergySourceTranslate translate = entity.getTranslations().get(lang);
        if (translate==null)
            translate = entity.getTranslations().get(defLang);

        if (translate!=null) {
            entity.setName(translate.getName());
            entity.setShortName(translate.getShortName());
            entity.setAddress(translate.getAddress());
        }

        return entity;
    }

    @Inject
    private Lang defLang;
}
