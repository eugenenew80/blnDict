package kz.kegoc.bln.translator.dict;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.MeterType;
import kz.kegoc.bln.entity.dict.translate.MeterTypeTranslate;
import kz.kegoc.bln.translator.Translator;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

@Stateless
public class MeterTypeTranslatorImpl implements Translator<MeterType> {
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public MeterType translate(MeterType entity, Lang lang) {
        entity.setLang(lang);

        if (entity.getTranslations()==null)
            return entity;

        MeterTypeTranslate translate = entity.getTranslations().get(lang);
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
