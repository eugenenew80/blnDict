package kz.kegoc.bln.translator.dict;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.BusinessPartner;
import kz.kegoc.bln.entity.dict.translate.BusinessPartnerTranslate;
import kz.kegoc.bln.translator.Translator;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class BusinessPartnerTranslatorImpl implements Translator<BusinessPartner> {
    public BusinessPartner translate(BusinessPartner entity, Lang lang) {
        entity.setLang(lang);

        if (entity.getTranslations()==null)
            return entity;

        BusinessPartnerTranslate translate = entity.getTranslations().get(lang);
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
