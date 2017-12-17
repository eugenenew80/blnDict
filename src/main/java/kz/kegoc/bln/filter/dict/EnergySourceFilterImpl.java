package kz.kegoc.bln.filter.dict;

import kz.kegoc.bln.entity.dict.Organization;
import kz.kegoc.bln.webapi.filters.SessionContext;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.EnergySource;
import kz.kegoc.bln.entity.dict.translate.EnergySourceTranslate;
import kz.kegoc.bln.filter.AbstractFilter;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.EnergySourceService;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashMap;

@Stateless
public class EnergySourceFilterImpl extends AbstractFilter<EnergySource> implements Filter<EnergySource> {
    public EnergySource filter(EnergySource entity, SessionContext context) {
        return translate(prepare(entity, context), context);
    }

    private EnergySource prepare(EnergySource entity, SessionContext context) {
        if (entity.getId()!=null) {
            EnergySource curEntity = service.findById(entity.getId(), context);

            entity.setCreateDate(curEntity.getCreateDate());
            entity.setCreateBy(curEntity.getCreateBy());

            if (entity.getTranslations()==null)
                entity.setTranslations(curEntity.getTranslations());
        }

        if (entity.getOrg()==null)
            entity.setOrg(new Organization(context.getUser().getOrgId()));

        if (entity.getTranslations()==null)
            entity.setTranslations(new HashMap<>());

        entity = addUpdateInfo(entity, context);
        return entity;
    }

    private EnergySource translate(EnergySource entity, SessionContext context) {
        Lang lang = entity.getLang()!=null ? entity.getLang() : defLang;

        EnergySourceTranslate translate = entity.getTranslations().getOrDefault(lang, new EnergySourceTranslate());
        translate = addUpdateInfo(translate, context);
        translate.setLang(lang);
        translate.setEnergySource(entity);
        translate.setName(entity.getName());
        translate.setShortName(entity.getShortName());
        translate.setAddress(entity.getAddress());
        entity.getTranslations().put(lang, translate);

        return entity;
    }

    @Inject
    private EnergySourceService service;

    @Inject
    private Lang defLang;
}
