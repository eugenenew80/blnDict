package kz.kegoc.bln.translator.dict;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.PowerLine;
import kz.kegoc.bln.entity.dict.translate.PowerLineTranslate;
import kz.kegoc.bln.translator.Translator;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

@Stateless
public class PowerLineTranslatorImpl implements Translator<PowerLine> {
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public PowerLine translate(PowerLine entity, Lang lang) {
        entity.setLang(lang);

        if (entity.getTranslations()==null)
            return entity;

        PowerLineTranslate translate = entity.getTranslations().get(lang);
        if (translate==null)
            translate = entity.getTranslations().get(defLang);

        if (translate!=null) {
            entity.setName(translate.getName());
            entity.setPropertyBoundary(translate.getPropertyBoundary());
        }

        return entity;
    }

    @Inject
    private Lang defLang;
}
