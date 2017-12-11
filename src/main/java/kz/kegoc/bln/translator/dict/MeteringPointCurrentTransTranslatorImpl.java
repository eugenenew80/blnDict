package kz.kegoc.bln.translator.dict;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.MeteringPointCurrentTrans;
import kz.kegoc.bln.entity.dict.translate.MeteringPointCurrentTransTranslate;
import kz.kegoc.bln.translator.Translator;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class MeteringPointCurrentTransTranslatorImpl implements Translator<MeteringPointCurrentTrans> {
    public MeteringPointCurrentTrans translate(MeteringPointCurrentTrans entity, Lang lang) {
        entity.setLang(lang);

        if (entity.getTranslations()==null)
            return entity;

        MeteringPointCurrentTransTranslate translate = entity.getTranslations().get(lang);
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
