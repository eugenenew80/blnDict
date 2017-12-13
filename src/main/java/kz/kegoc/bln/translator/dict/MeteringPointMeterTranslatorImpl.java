package kz.kegoc.bln.translator.dict;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.MeteringPointMeter;
import kz.kegoc.bln.translator.Translator;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

@Stateless
public class MeteringPointMeterTranslatorImpl implements Translator<MeteringPointMeter> {
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public MeteringPointMeter translate(MeteringPointMeter entity, Lang lang) {
        entity.setLang(lang);
        return entity;
    }

    @Inject
    private Lang defLang;
}
