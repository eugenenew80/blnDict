package kz.kegoc.bln.filter.dict;

import kz.kegoc.bln.webapi.filters.SessionContext;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.PowerLine;
import kz.kegoc.bln.entity.dict.PowerLinePart;
import kz.kegoc.bln.entity.dict.translate.PowerLineTranslate;
import kz.kegoc.bln.filter.AbstractFilter;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.OrganizationService;
import kz.kegoc.bln.service.dict.PowerLineService;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;

@Stateless
public class PowerLineFilterImpl extends AbstractFilter<PowerLine> implements Filter<PowerLine> {
    public PowerLine filter(PowerLine entity, SessionContext context) {
        return translate(prepare(entity, context), context);
    }

    private PowerLine prepare(PowerLine entity, SessionContext context) {
        if (entity.getId()!=null) {
            PowerLine curEntity = service.findById(entity.getId(), null);

            entity.setCreateDate(curEntity.getCreateDate());
            entity.setCreateBy(curEntity.getCreateBy());

            if (entity.getTranslations()==null)
                entity.setTranslations(curEntity.getTranslations());

            if (entity.getPowerLineParts()==null)
                entity.setPowerLineParts(curEntity.getPowerLineParts());
        }

        if (entity.getOrg()==null)
            entity.setOrg(organizationService.findById(context.getUser().getOrgId(), context));

        if (entity.getTranslations()==null)
            entity.setTranslations(new HashMap<>());

        entity = addPowerLinePart(entity, context);
        entity = addUpdateInfo(entity, context);
        return entity;
    }

    private PowerLine addPowerLinePart(PowerLine entity, SessionContext context) {
        if (entity.getPowerLineParts()==null)
            entity.setPowerLineParts(new ArrayList<>());

        if (entity.getPowerLineParts().size()==0) {
            PowerLinePart linePart = new PowerLinePart();
            linePart.setPowerLine(entity);
            linePart.setName(entity.getName());
            linePart.setLength(entity.getLength());
            linePart.setR(entity.getR());
            linePart.setOrg(entity.getOrg());
            linePart = powerLinePartFilter.filter(linePart, context);
            entity.getPowerLineParts().add(linePart);
        }

        return entity;
    }

    private PowerLine translate(PowerLine entity, SessionContext context) {
        Lang lang = entity.getLang()!=null ? entity.getLang() : defLang;

        PowerLineTranslate translate = entity.getTranslations().getOrDefault(lang, new PowerLineTranslate());
        translate = addUpdateInfo(translate, context);
        translate.setLang(lang);
        translate.setPowerLine(entity);
        translate.setName(entity.getName());
        translate.setPropertyBoundary(entity.getPropertyBoundary());
        entity.getTranslations().put(lang, translate);

        return entity;
    }

    @Inject
    private PowerLineService service;

    @Inject
    private OrganizationService organizationService;

    @Inject
    private Lang defLang;

    @Inject
    private Filter<PowerLinePart> powerLinePartFilter;
}
