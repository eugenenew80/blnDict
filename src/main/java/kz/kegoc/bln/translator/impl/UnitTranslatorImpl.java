package kz.kegoc.bln.translator.impl;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.Unit;
import kz.kegoc.bln.entity.dict.translate.UnitTranslate;
import kz.kegoc.bln.translator.Translator;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class UnitTranslatorImpl implements Translator<Unit> {
    public Unit translate(Unit entity, Lang lang) {
        entity.setLang(lang);

        if (entity.getTranslations()==null)
            return entity;

        UnitTranslate translate = entity.getTranslations().get(lang);
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
