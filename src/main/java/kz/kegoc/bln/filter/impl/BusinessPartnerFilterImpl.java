package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.ejb.SessionContext;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.BusinessPartner;
import kz.kegoc.bln.entity.dict.translate.BusinessPartnerTranslate;
import kz.kegoc.bln.filter.AbstractFilter;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.BusinessPartnerService;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashMap;

@Stateless
public class BusinessPartnerFilterImpl extends AbstractFilter<BusinessPartner> implements Filter<BusinessPartner> {
    public BusinessPartner filter(BusinessPartner entity, SessionContext context) {
        return translate(prepare(entity, context), context);
    }

    private BusinessPartner prepare(BusinessPartner entity, SessionContext context) {
        if (entity.getId()!=null) {
            BusinessPartner curEntity = service.findById(entity.getId(), context);

            entity.setCreateDate(curEntity.getCreateDate());
            entity.setCreateBy(curEntity.getCreateBy());

            if (entity.getTranslations()==null)
                entity.setTranslations(curEntity.getTranslations());
        }

        if (entity.getTranslations()==null)
            entity.setTranslations(new HashMap<>());

        entity = addUpdateInfo(entity, context);
        return entity;
    }

    private BusinessPartner translate(BusinessPartner entity, SessionContext context) {
        Lang lang = entity.getLang()!=null ? entity.getLang() : defLang;

        BusinessPartnerTranslate translate = entity.getTranslations().getOrDefault(lang, new BusinessPartnerTranslate());
        translate = addUpdateInfo(translate, context);
        translate.setLang(lang);
        translate.setBusinessPartner(entity);
        translate.setName(entity.getName());
        entity.getTranslations().put(lang, translate);

        return entity;
    }

    @Inject
    private BusinessPartnerService service;

    @Inject
    private Lang defLang;
}
