package kz.kegoc.bln.translator.dict;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.EnergyDistrict;
import kz.kegoc.bln.entity.dict.translate.EnergyDistrictTranslate;
import kz.kegoc.bln.translator.Translator;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

@Stateless
public class EnergyDistrictTranslatorImpl implements Translator<EnergyDistrict> {
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public EnergyDistrict translate(EnergyDistrict entity, Lang lang) {
        entity.setLang(lang);

        if (entity.getTranslations()==null)
            return entity;

        EnergyDistrictTranslate translate = entity.getTranslations().get(lang);
        if (translate==null)
            translate = entity.getTranslations().get(defLang);

        if (translate!=null) {
            entity.setName(translate.getName());
            entity.setShortName(translate.getShortName());
        }

        return entity;
    }

    @Inject
    private Lang defLang;
}
