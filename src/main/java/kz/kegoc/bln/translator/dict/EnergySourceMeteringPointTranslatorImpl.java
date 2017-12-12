package kz.kegoc.bln.translator.dict;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.EnergySourceMeteringPoint;
import kz.kegoc.bln.translator.Translator;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class EnergySourceMeteringPointTranslatorImpl implements Translator<EnergySourceMeteringPoint> {
    public EnergySourceMeteringPoint translate(EnergySourceMeteringPoint entity, Lang lang) {
        entity.setLang(lang);
        return entity;
    }

    @Inject
    private Lang defLang;
}
