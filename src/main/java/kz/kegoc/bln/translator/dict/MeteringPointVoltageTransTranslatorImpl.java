package kz.kegoc.bln.translator.dict;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.MeteringPointVoltageTrans;
import kz.kegoc.bln.entity.dict.translate.MeteringPointVoltageTransTranslate;
import kz.kegoc.bln.translator.Translator;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class MeteringPointVoltageTransTranslatorImpl implements Translator<MeteringPointVoltageTrans> {
    public MeteringPointVoltageTrans translate(MeteringPointVoltageTrans entity, Lang lang) {
        entity.setLang(lang);

        if (entity.getTranslations()==null)
            return entity;

        MeteringPointVoltageTransTranslate translate = entity.getTranslations().get(lang);
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
