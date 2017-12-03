package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.ejb.SessionContext;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.BusinessPartner;
import kz.kegoc.bln.entity.dict.translate.BusinessPartnerTranslate;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.BusinessPartnerService;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.HashMap;

@Stateless
public class BusinessPartnerFilterImpl implements Filter<BusinessPartner> {
    public BusinessPartner filter(BusinessPartner entity, SessionContext context) {
        return translate(prepare(entity));
    }

    private BusinessPartner prepare(BusinessPartner entity) {
        if (entity.getId()!=null) {
            BusinessPartner curEntity = businessPartnerService.findById(entity.getId(), null);

            entity.setCreateDate(curEntity.getCreateDate());
            entity.setCreateBy(curEntity.getCreateBy());

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
        if (translate.getId()==null)
            translate.setCreateDate(LocalDateTime.now());
        else
            translate.setLastUpdateDate(LocalDateTime.now());

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
