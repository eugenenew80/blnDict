package kz.kegoc.bln.translator.dict;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.SubstationMeteringPoint;
import kz.kegoc.bln.translator.Translator;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class SubstationMeteringPointTranslatorImpl implements Translator<SubstationMeteringPoint> {
    public SubstationMeteringPoint translate(SubstationMeteringPoint entity, Lang lang) {
        entity.setLang(lang);
        return entity;
    }

    @Inject
    private Lang defLang;
}
