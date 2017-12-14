package kz.kegoc.bln.translator.dict;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.MeteringPoint;
import kz.kegoc.bln.entity.dict.translate.MeteringPointTranslate;
import kz.kegoc.bln.translator.Translator;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

@Stateless
public class MeteringPointTranslatorImpl implements Translator<MeteringPoint> {
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public MeteringPoint translate(MeteringPoint entity, Lang lang) {
        entity.setLang(lang);

        if (entity.getTranslations()==null)
            return entity;

        MeteringPointTranslate translate = entity.getTranslations().get(lang);
        if (translate==null)
            translate = entity.getTranslations().get(defLang);

        if (translate!=null) {
            entity.setName(translate.getName());
            entity.setShortName(translate.getShortName());
            entity.setPropertyBoundary(translate.getPropertyBoundary());
            entity.setResponsibilityZone1(translate.getResponsibilityZone1());
            entity.setResponsibilityZone2(translate.getResponsibilityZone2());
        }

        return entity;
    }

    @Inject
    private Lang defLang;
}
