package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.ejb.SessionContext;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.PowerLinePart;
import kz.kegoc.bln.entity.dict.translate.PowerLinePartTranslate;
import kz.kegoc.bln.filter.AbstractFilter;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.OrganizationService;
import kz.kegoc.bln.service.dict.PowerLinePartService;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashMap;

@Stateless
public class PowerLinePartFilterImpl extends AbstractFilter<PowerLinePart> implements Filter<PowerLinePart> {
    public PowerLinePart filter(PowerLinePart entity, SessionContext context) {
        return translate(prepare(entity, context), context);
    }

    private PowerLinePart prepare(PowerLinePart entity, SessionContext context) {
        if (entity.getId()!=null) {
            PowerLinePart curEntity = service.findById(entity.getId(), null);

            entity.setCreateDate(curEntity.getCreateDate());
            entity.setCreateBy(curEntity.getCreateBy());

            if (entity.getTranslations()==null)
                entity.setTranslations(curEntity.getTranslations());
        }

        if (entity.getBusinessPartner()!=null && entity.getBusinessPartner().getId()==null)
            entity.setBusinessPartner(null);

        if (entity.getOrg()!=null && entity.getOrg().getId()==null)
            entity.setOrg(null);

        if (entity.getOrg()==null)
            entity.setOrg(organizationService.findById(context.getUser().getOrgId(), context));

        if (entity.getTranslations()==null)
            entity.setTranslations(new HashMap<>());

        entity = addUpdateInfo(entity, context);
        return entity;
    }

    private PowerLinePart translate(PowerLinePart entity, SessionContext context) {
        Lang lang = entity.getLang()!=null ? entity.getLang() : defLang;

        PowerLinePartTranslate translate = entity.getTranslations().getOrDefault(lang, new PowerLinePartTranslate());
        translate = addUpdateInfo(translate, context);
        translate.setLang(lang);
        translate.setPowerLinePart(entity);
        translate.setName(entity.getName());
        entity.getTranslations().put(lang, translate);

        return entity;
    }

    @Inject
    private PowerLinePartService service;

    @Inject
    private OrganizationService organizationService;

    @Inject
    private Lang defLang;
}
