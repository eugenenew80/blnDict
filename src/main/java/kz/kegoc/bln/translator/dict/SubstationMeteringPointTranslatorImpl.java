package kz.kegoc.bln.translator.dict;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.SubstationMeteringPoint;
import kz.kegoc.bln.translator.Translator;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

@Stateless
public class SubstationMeteringPointTranslatorImpl implements Translator<SubstationMeteringPoint> {
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public SubstationMeteringPoint translate(SubstationMeteringPoint entity, Lang lang) {
        entity.setLang(lang);
        return entity;
    }

    @Inject
    private Lang defLang;
}
