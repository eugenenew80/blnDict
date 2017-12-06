package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.ejb.SessionContext;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.MeteringPoint;
import kz.kegoc.bln.entity.dict.translate.MeteringPointTranslate;
import kz.kegoc.bln.filter.AbstractFilter;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.MeteringPointService;
import kz.kegoc.bln.service.dict.OrganizationService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashMap;

@Stateless
public class MeteringPointFilterImpl extends AbstractFilter<MeteringPoint> implements Filter<MeteringPoint> {
    public MeteringPoint filter(MeteringPoint entity, SessionContext context) {
        return translate(prepare(entity, context), context);
    }

    private MeteringPoint prepare(MeteringPoint entity, SessionContext context) {
        if (entity.getId()!=null) {
            MeteringPoint curEntity = meteringPointService.findById(entity.getId(), null);

            entity.setCreateDate(curEntity.getCreateDate());
            entity.setCreateBy(curEntity.getCreateBy());

            if (entity.getTranslations()==null)
                entity.setTranslations(curEntity.getTranslations());
        }

        if (entity.getBusinessPartner1()!=null && entity.getBusinessPartner1().getId()==null)
            entity.setBusinessPartner1(null);

        if (entity.getBusinessPartner2()!=null && entity.getBusinessPartner2().getId()==null)
            entity.setBusinessPartner2(null);

        if (entity.getOrg()!=null && entity.getOrg().getId()==null)
            entity.setOrg(null);


        if (entity.getOrg()==null)
            entity.setOrg(organizationService.findById(1L, context));

        if (entity.getTranslations()==null)
            entity.setTranslations(new HashMap<>());

        entity = addUpdateInfo(entity, context);
        return entity;
    }

    private MeteringPoint translate(MeteringPoint entity, SessionContext context) {
        Lang lang = entity.getLang()!=null ? entity.getLang() : defLang;

        MeteringPointTranslate translate = entity.getTranslations().getOrDefault(lang, new MeteringPointTranslate());
        translate = addUpdateInfo(translate, context);
        translate.setLang(lang);
        translate.setMeteringPoint(entity);
        translate.setName(entity.getName());
        translate.setShortName(entity.getShortName());
        entity.getTranslations().put(lang, translate);

        return entity;
    }

    @Inject
    private MeteringPointService meteringPointService;

    @Inject
    private OrganizationService organizationService;

    @Inject
    private Lang defLang;
}
