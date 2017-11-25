package kz.kegoc.bln.translator.impl;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.VoltageTransType;
import kz.kegoc.bln.entity.dict.translate.VoltageTransTypeTranslate;
import kz.kegoc.bln.translator.Translator;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class VoltageTransTypeTranslatorImpl implements Translator<VoltageTransType> {
    public VoltageTransType translate(VoltageTransType entity, Lang lang) {
        entity.setLang(lang);

        if (entity.getTranslations()==null)
            return entity;

        VoltageTransTypeTranslate translate = entity.getTranslations().get(lang);
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
