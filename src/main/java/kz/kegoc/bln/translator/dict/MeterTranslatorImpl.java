package kz.kegoc.bln.translator.dict;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.Meter;
import kz.kegoc.bln.entity.dict.translate.MeterTranslate;
import kz.kegoc.bln.translator.Translator;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class MeterTranslatorImpl implements Translator<Meter> {
    public Meter translate(Meter entity, Lang lang) {
        entity.setLang(lang);

        if (entity.getTranslations()==null)
            return entity;

        MeterTranslate translate = entity.getTranslations().get(lang);
        if (translate==null)
            translate = entity.getTranslations().get(defLang);

        if (translate!=null) {
            entity.setName(translate.getName());
            entity.setManufacturer(translate.getManufacturer());
        }

        return entity;
    }

    @Inject
    private Lang defLang;
}
