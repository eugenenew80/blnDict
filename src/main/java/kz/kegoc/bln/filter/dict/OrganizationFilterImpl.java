package kz.kegoc.bln.filter.dict;

import kz.kegoc.bln.webapi.filters.SessionContext;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.Organization;
import kz.kegoc.bln.entity.dict.translate.OrganizationTranslate;
import kz.kegoc.bln.filter.AbstractFilter;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.OrganizationService;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashMap;

@Stateless
public class OrganizationFilterImpl extends AbstractFilter<Organization> implements Filter<Organization> {
    public Organization filter(Organization entity, SessionContext context) {
        return translate(prepare(entity, context), context);
    }

    private Organization prepare(Organization entity, SessionContext context) {
        if (entity.getId()!=null) {
            Organization curEntity = companyService.findById(entity.getId(), null);

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

    private Organization translate(Organization entity, SessionContext context) {
        Lang lang = entity.getLang()!=null ? entity.getLang() : defLang;

        OrganizationTranslate translate = entity.getTranslations().getOrDefault(lang, new OrganizationTranslate());
        translate = addUpdateInfo(translate, context);
        translate.setLang(lang);
        translate.setOrg(entity);
        translate.setName(entity.getName());
        translate.setShortName(entity.getShortName());
        entity.getTranslations().put(lang, translate);

        return entity;
    }

    @Inject
    private OrganizationService companyService;

    @Inject
    private Lang defLang;
}
