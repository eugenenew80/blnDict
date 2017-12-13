package kz.kegoc.bln.translator.dict;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.EnergySourceMeteringPoint;
import kz.kegoc.bln.translator.Translator;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

@Stateless
public class EnergySourceMeteringPointTranslatorImpl implements Translator<EnergySourceMeteringPoint> {
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public EnergySourceMeteringPoint translate(EnergySourceMeteringPoint entity, Lang lang) {
        entity.setLang(lang);
        return entity;
    }

    @Inject
    private Lang defLang;
}
