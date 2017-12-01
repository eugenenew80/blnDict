package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.BusinessPartner;
import kz.kegoc.bln.entity.dict.translate.BusinessPartnerTranslate;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.BusinessPartnerService;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashMap;

@Stateless
public class BusinessPartnerFilterImpl implements Filter<BusinessPartner> {
    public BusinessPartner filter(BusinessPartner entity) {
        return translate(prepare(entity));
    }

    private BusinessPartner prepare(BusinessPartner entity) {
        if (entity.getId()!=null) {
            BusinessPartner curEntity = businessPartnerService.findById(entity.getId());

            if (entity.getTranslations()==null)
                entity.setTranslations(curEntity.getTranslations());
        }

        if (entity.getParentBusinessPartner()!=null && entity.getParentBusinessPartner().getId()==null)
            entity.setParentBusinessPartner(null);

        if (entity.getTranslations()==null)
            entity.setTranslations(new HashMap<>());

        return entity;
    }

    private BusinessPartner translate(BusinessPartner entity) {
        Lang lang = entity.getLang()!=null ? entity.getLang() : defLang;

        BusinessPartnerTranslate translate = entity.getTranslations().getOrDefault(lang, new BusinessPartnerTranslate());
        translate.setLang(lang);
        translate.setBusinessPartner(entity);
        translate.setName(entity.getName());
        entity.getTranslations().put(lang, translate);

        return entity;
    }

    @Inject
    private BusinessPartnerService businessPartnerService;

    @Inject
    private Lang defLang;
}
